package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.dto.request.AddToProfileInfoRequest;
import com.example.korepetycjebackend.models.Paragraph;
import com.example.korepetycjebackend.models.Subject;
import com.example.korepetycjebackend.models.Teacher;
import com.example.korepetycjebackend.repositories.TeacherRepository;
import com.example.korepetycjebackend.repositories.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;
    public List<Teacher> getAll(){
        return teacherRepository.findAll();
    }

    public List<Teacher> getBySubject(String subject){
        return teacherRepository.findBySubject(subject);
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

    public void addSubjects(UUID teacherId, List<String> subjectStrings){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new RuntimeException("teacher not found"));

        var teacherSubjects = teacher.getSubjects();

        subjectStrings.forEach(subject->{
            teacherSubjects.add(new Subject(subject));
        });

        teacher.setSubjects(teacherSubjects);

        teacherRepository.save(teacher);
    }

    public void addToProfileInfo(UUID teacherId, AddToProfileInfoRequest request){
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new RuntimeException("teacher not found"));

        var teacherProfileInfo = teacher.getProfileInfo();

        request.getParagraphs().forEach((paragraphDto -> {
            teacherProfileInfo.add(new Paragraph(paragraphDto));
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
}
