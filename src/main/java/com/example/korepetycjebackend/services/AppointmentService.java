package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.CreateAppointmentRequest;
import com.example.korepetycjebackend.models.Appointment;
import com.example.korepetycjebackend.repositories.AppointmentRepository;
import com.example.korepetycjebackend.repositories.ClientRepository;
import com.example.korepetycjebackend.repositories.SubjectRepository;
import com.example.korepetycjebackend.repositories.TeacherRepository;
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

    public UUID createAppointment(CreateAppointmentRequest request){
//        var subject = subjectRepository.findById(request.getSubject())
//                .orElseThrow(()-> new RuntimeException("subject not found"));
//
//        var client = clientRepository.findById(request.getClientId())
//                .orElseThrow(()->new RuntimeException("client not found"));

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

//    public List<Appointment> getByTeacherId(UUID teacherId){
//        return appointmentRepository.findByTeacherId(teacherId);
//    }
//
//    public List<Appointment> getByClientId(UUID clientId){
//        return appointmentRepository.findByClientId(clientId);
//    }

    public void deleteAppointment(UUID appointmentId){
        appointmentRepository.deleteById(appointmentId);
    }
}
