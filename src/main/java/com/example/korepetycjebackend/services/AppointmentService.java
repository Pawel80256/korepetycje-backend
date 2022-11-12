package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.CreateAppointmentRequest;
import com.example.korepetycjebackend.models.Appointment;
import com.example.korepetycjebackend.repositories.AppointmentRepository;
import com.example.korepetycjebackend.repositories.ClientRepository;
import com.example.korepetycjebackend.repositories.SubjectRepository;
import com.example.korepetycjebackend.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final SubjectRepository subjectRepository;
    private final ClientRepository clientRepository;
    private final TeacherRepository teacherRepository;

    public UUID createAppointment(CreateAppointmentRequest request){
        var subject = subjectRepository.findById(request.getSubject())
                .orElseThrow(()-> new RuntimeException("subject not found"));

        var client = clientRepository.findById(request.getClientId())
                .orElseThrow(()->new RuntimeException("client not found"));

        var teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(()->new RuntimeException("teacher not found"));

        var appointment = Appointment.builder()
                .id(UUID.randomUUID())
                .client(client)
                .teacher(teacher)
                .date(request.getDate())
                .subject(subject)
                .build();

        appointmentRepository.save(appointment);

        return appointment.getId();
    }

    public void deleteAppointment(UUID appointmentId){
        appointmentRepository.deleteById(appointmentId);
    }
}
