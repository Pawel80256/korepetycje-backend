package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.AppointmentDto;
import com.example.korepetycjebackend.dto.OpinionDto;
import com.example.korepetycjebackend.dto.TeacherDto;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.dto.request.AddToProfileInfoRequest;
import com.example.korepetycjebackend.dto.request.UpdateParagraphRequest;
import com.example.korepetycjebackend.models.Paragraph;
import com.example.korepetycjebackend.models.Subject;
import com.example.korepetycjebackend.models.Teacher;
import com.example.korepetycjebackend.repositories.ParagraphRepository;
import com.example.korepetycjebackend.repositories.SubjectRepository;
import com.example.korepetycjebackend.repositories.TeacherRepository;
import com.example.korepetycjebackend.repositories.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserDataRepository userDataRepository;
    private final ParagraphRepository paragraphRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    public List<Teacher> getAll(){
        return teacherRepository.findAll();
    }

    public List<Teacher> getBySubject(String subject){
        return teacherRepository.findBySubject(subject);
    }

    public TeacherDto getById(UUID id){
        var teacher = teacherRepository.findById(id).orElseThrow(()-> new RuntimeException("teacher not found"));
        return TeacherDto.builder()
                .id(teacher.getId())
                .city(teacher.getCity())
                .userData(teacher.getUserData())
                .subjects(teacher.getSubjects())
                .profileInfo(teacher.getProfileInfo())
                .opinions(
                        teacher.getOpinions().stream()
                        .map(opinion -> modelMapper.map(opinion, OpinionDto.class))
                        .collect(Collectors.toList())
                )
                .appointments(
                        teacher.getAppointments().stream()
                                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                                .collect(Collectors.toList())
                )
                .build();
    }

    public List<Subject> getSubjectsByTeacherId(UUID teacherId){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new RuntimeException("teacher not found"));
        return teacher.getSubjects();
    }

    public UUID createTeacher(RegisterRequest registerRequest){

        if(userDataRepository.existsByEmailAddress(registerRequest.getEmailAddress())){
            throw new RuntimeException("email taken");
        }

        registerRequest.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );

        var teacher = new Teacher(registerRequest);
        teacherRepository.save(teacher);
        return teacher.getId();
    }

    public List<TeacherDto> getAllBySubjectAndCity(String subjectName, String city){
        var teachers = teacherRepository.findByCity(city);
        var subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new RuntimeException("Teacher's subject not found"));
        var teachersBySubject = teachers.stream()
                .filter(teacher -> teacher.getSubjects().contains(subject))
                .collect(Collectors.toList());
        List<TeacherDto> result = new ArrayList<>();
        teachersBySubject.forEach(teacher -> {
            result.add(TeacherDto.builder()
                    .id(teacher.getId())
                    .city(teacher.getCity())
                    .userData(teacher.getUserData())
                    .subjects(teacher.getSubjects())
                    .profileInfo(teacher.getProfileInfo())
                    .opinions(
                            teacher.getOpinions().stream()
                                    .map(opinion -> modelMapper.map(opinion, OpinionDto.class))
                                    .collect(Collectors.toList())
                    )
                    .appointments(
                            teacher.getAppointments().stream()
                                    .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                                    .collect(Collectors.toList())
                    )
                    .build());
        });
        return result;
    }

    public void addSubject(UUID teacherId, String subjectName){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new RuntimeException("teacher not found"));

        var teacherSubjects = teacher.getSubjects();

        teacherSubjects.add(new Subject(subjectName));

        teacher.setSubjects(teacherSubjects);

        teacherRepository.save(teacher);
    }

    //todo: change list parameter to only one paragraph
    public void addToProfileInfo(UUID teacherId, AddToProfileInfoRequest request){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new RuntimeException("teacher not found"));

        var teacherProfileInfo = teacher.getProfileInfo();

        request.getParagraphs().forEach((paragraphDto -> {
            var order =
                    teacherProfileInfo.size() == 0
                    ? 0
                    : teacherProfileInfo.stream().map(Paragraph::getOrderr).max(Comparator.naturalOrder()).get() + 1;
            var paragraph = new Paragraph(paragraphDto, order);
            teacherProfileInfo.add(paragraph);
        }));

        teacher.setProfileInfo(teacherProfileInfo);

        teacherRepository.save(teacher);
    }

    public void deleteFromProfileInfo(UUID teacherId, UUID paragraphId){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new RuntimeException("teacher not found"));

        var teacherProfileInfo = teacher.getProfileInfo();

        teacherProfileInfo.removeIf(paragraph -> paragraph.getId().equals(paragraphId));

        teacher.setProfileInfo(teacherProfileInfo);

        teacherRepository.save(teacher);
    }

    public void deleteSubject(UUID teacherId, String subjectName){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()->new RuntimeException("teacher not found"));

        var teacherSubjects = teacher.getSubjects();
        teacherSubjects.removeIf(subject -> subject.getSubjectName().equals(subjectName));
        teacher.setSubjects(teacherSubjects);
        teacherRepository.save(teacher);
    }

    public void updateProfileInfo(UUID paragraphId, UpdateParagraphRequest updateParagraphRequest){
        var paragraph = paragraphRepository.findById(paragraphId)
                .orElseThrow(()->new RuntimeException("paragraph not found"));

        paragraph.setTitle(updateParagraphRequest.getTitle());
        paragraph.setContent(updateParagraphRequest.getContent());

        paragraphRepository.save(paragraph);
    }

    public void updateCity(UUID teacherId, String city){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()->new RuntimeException("teacher not found"));
        teacher.setCity(city);
        teacherRepository.save(teacher);
    }

    public void changeParagraphOrder(UUID teacherId, UUID paragraphId, boolean orderUp){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new RuntimeException("teacher not found"));
        var selectedParagraph = paragraphRepository.findById(paragraphId)
                .orElseThrow(()-> new RuntimeException("paragraph not found"));

        var teacherProfileInfo = teacher.getProfileInfo();
        teacherProfileInfo.sort(Comparator.comparing(Paragraph::getOrderr));

        int indexOfSelectedParagraph = teacherProfileInfo.indexOf(selectedParagraph);
        int indexOfNeighbourParagraph = orderUp ? indexOfSelectedParagraph - 1 : indexOfSelectedParagraph + 1;

        var neighbourParagraph = teacherProfileInfo.get(indexOfNeighbourParagraph);

        int orderOfSelectedParagraph = selectedParagraph.getOrderr();
        int orderOfNeighbourParagraph = neighbourParagraph.getOrderr();

        selectedParagraph.setOrderr(orderOfNeighbourParagraph);
        neighbourParagraph.setOrderr(orderOfSelectedParagraph);

        paragraphRepository.saveAll(Arrays.asList(selectedParagraph,neighbourParagraph));

    }
}
