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
                    .role("CLIENT")
                    .build();
            var client = Client.builder()
                    .id(UUID.randomUUID())
                    .userData(userData)
                    .build();
            clientRepository.save(client);

            var userData2 = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("clientFirstName2")
                    .lastName("clientLastNam2e")
                    .emailAddress("client2@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .role("CLIENT")
                    .build();
            var client2 = Client.builder()
                    .id(UUID.randomUUID())
                    .userData(userData2)
                    .build();

            clientRepository.save(client2);

            var userData3 = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("clientFirstName3")
                    .lastName("clientLastName3")
                    .emailAddress("client3@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .role("CLIENT")
                    .build();
            var client3 = Client.builder()
                    .id(UUID.randomUUID())
                    .userData(userData3)
                    .build();
            clientRepository.save(client3);

        }

        if(teacherRepository.count()==0){
            var userData = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("teacherFirstName")
                    .lastName("teacherLastName")
                    .emailAddress("teacher@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .role("TEACHER")
                    .build();
            var teacher = Teacher.builder()
                    .id(UUID.randomUUID())
                    .userData(userData)
                    .city("Białystok")
                    .subjects(Arrays.asList(new Subject("matematyka"),new Subject("angielski")))
                    .build();
            teacherRepository.save(teacher);

            var userData2 = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("teacherFirstName2")
                    .lastName("teacherLastName2")
                    .emailAddress("teacher2@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .role("TEACHER")
                    .build();
            var teacher2 = Teacher.builder()
                    .id(UUID.randomUUID())
                    .userData(userData2)
                    .city("Warszawa")
                    .subjects(Arrays.asList(new Subject("informatyka")))
                    .build();
            teacherRepository.save(teacher2);

            var userData3 = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("teacherFirstName3")
                    .lastName("teacherLastName3")
                    .emailAddress("teacher3@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .role("TEACHER")
                    .build();
            var teacher3 = Teacher.builder()
                    .id(UUID.randomUUID())
                    .userData(userData3)
                    .city("Zambrów")
                    .subjects(Arrays.asList(new Subject("biologia"),new Subject("chemia")))
                    .build();

            teacherRepository.save(teacher3);

            var userData4 = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("teacherFirstName4")
                    .lastName("teacherLastName4")
                    .emailAddress("teacher4@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .role("TEACHER")
                    .build();
            var teacher4 = Teacher.builder()
                    .id(UUID.randomUUID())
                    .userData(userData4)
                    .city("Gdańsk")
                    .subjects(Arrays.asList(new Subject("matematyka"),new Subject("informatyka")))
                    .build();
            teacherRepository.save(teacher4);

        }
    }
}
