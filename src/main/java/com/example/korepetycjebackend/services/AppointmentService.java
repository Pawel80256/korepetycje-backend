package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.CreateAppointmentRequest;
import com.example.korepetycjebackend.models.Appointment;
import com.example.korepetycjebackend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final SubjectRepository subjectRepository;
    private final ClientRepository clientRepository;
    private final TeacherRepository teacherRepository;
    private final UserDataRepository userDataRepository;

    public UUID createAppointment(CreateAppointmentRequest request){

        var teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(()->new RuntimeException("teacher not found"));

        var appointment = Appointment.builder()
                .id(UUID.randomUUID())
                .date(request.getDate())
                .subject(null)
                .build();

        appointmentRepository.save(appointment);

        var teacherAppointments = teacher.getAppointments();
        teacherAppointments.add(appointment);
        teacherRepository.save(teacher);

        return appointment.getId();
    }

    public void bookAppointment(UUID appointmentId, UUID userDataId, String subjectName){
        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new RuntimeException("appointment not found"));
        var client = clientRepository.findByUserDataId(userDataId)
                .orElseThrow(() -> new RuntimeException("client not found"));
        var subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new RuntimeException("subject not found"));
        appointment.setSubject(subject);
        appointmentRepository.save(appointment);

        var clientAppointments = client.getAppointments();
        clientAppointments.add(appointment);
        client.setAppointments(clientAppointments);
        clientRepository.save(client);

    }


    public void deleteAppointment(UUID appointmentId){
        appointmentRepository.deleteById(appointmentId);
    }
}
