package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.AppointmentDto;
import com.example.korepetycjebackend.dto.request.CreateAppointmentRequest;
import com.example.korepetycjebackend.models.Appointment;
import com.example.korepetycjebackend.models.Teacher;
import com.example.korepetycjebackend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final SubjectRepository subjectRepository;
    private final ClientRepository clientRepository;
    private final TeacherRepository teacherRepository;
    private final UserDataRepository userDataRepository;
    private final ModelMapper modelMapper;

    public List<AppointmentDto> getAllAppointments(){
        return appointmentRepository.findAll().stream()
                .map(appointment -> modelMapper.map(appointment,AppointmentDto.class))
                .collect(Collectors.toList());
    }
    public UUID createAppointment(CreateAppointmentRequest request){

        var teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(()->new RuntimeException("teacher not found"));

        var date = request.getDate().plusHours(1);

        var appointment = Appointment.builder()
                .id(UUID.randomUUID())
                .date(date)
                .subject(null)
                .teacher(teacher)
                .build();

        appointmentRepository.save(appointment);

        var teacherAppointments = teacher.getAppointments();
        teacherAppointments.add(appointment);
        teacherRepository.save(teacher);

        return appointment.getId();
    }

    public void bookAppointment(UUID appointmentId, UUID clientId, String subjectName){
        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new RuntimeException("appointment not found"));
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("client not found"));
        var subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new RuntimeException("subject not found"));
        appointment.setSubject(subject);
        appointment.setClient(client);
        appointmentRepository.save(appointment);

        var clientAppointments = client.getAppointments();
        clientAppointments.add(appointment);
        client.setAppointments(clientAppointments);
        clientRepository.save(client);
    }

    public void cancelBooking(UUID appointmentId, UUID clientId){
        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new RuntimeException("appointment not found"));
        var client = clientRepository.findById(clientId)
                        .orElseThrow(()-> new RuntimeException("client not found"));
        var clientAppointments = client.getAppointments();
        clientAppointments.remove(appointment);
        client.setAppointments(clientAppointments);
        appointment.setClient(null);
        appointment.setSubject(null);
        clientRepository.save(client);
        appointmentRepository.save(appointment);
    }


    public void deleteAppointment(UUID appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()->new RuntimeException("Appointment does note exists"));
        Teacher teacher = appointment.getTeacher();
        teacher.getAppointments().remove(appointment);
        teacherRepository.save(teacher);
//        appointmentRepository.deleteById(appointmentId);
    }
}
