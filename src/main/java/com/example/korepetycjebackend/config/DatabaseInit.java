package com.example.korepetycjebackend.config;

import com.example.korepetycjebackend.models.Client;
import com.example.korepetycjebackend.models.Subject;
import com.example.korepetycjebackend.models.Teacher;
import com.example.korepetycjebackend.models.UserData;
import com.example.korepetycjebackend.repositories.AppointmentRepository;
import com.example.korepetycjebackend.repositories.ClientRepository;
import com.example.korepetycjebackend.repositories.TeacherRepository;
import com.example.korepetycjebackend.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseInit {
    private final ClientRepository clientRepository;
    private final TeacherRepository teacherRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init(){
        if(clientRepository.count()==0){
            var userData = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("clientFirstName")
                    .lastName("clientLastName")
                    .emailAddress("client@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .build();
            var client = Client.builder()
                    .id(UUID.randomUUID())
                    .userData(userData)
                    .build();
            clientRepository.save(client);

        }

        if(teacherRepository.count()==0){
            var userData = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("teacherFirstName")
                    .lastName("teacherLastName")
                    .emailAddress("teacher@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .build();
            var teacher = Teacher.builder()
                    .id(UUID.randomUUID())
                    .userData(userData)
                    .subjects(Arrays.asList(new Subject("matematyka"),new Subject("angielski")))
                    .build();
            teacherRepository.save(teacher);

        }
    }
}
