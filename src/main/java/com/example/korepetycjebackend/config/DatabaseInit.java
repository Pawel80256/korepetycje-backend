package com.example.korepetycjebackend.config;

import com.example.korepetycjebackend.dto.ParagraphDto;
import com.example.korepetycjebackend.models.*;
import com.example.korepetycjebackend.repositories.AdminRepository;
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
    private final AdminRepository adminRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init(){
        if(adminRepository.count() == 0){
            var userData = UserData.builder()
                    .id(UUID.randomUUID())
                    .firstName("admin")
                    .lastName("admin")
                    .emailAddress("admin@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .role("ADMIN")
                    .build();
            var admin = Admin.builder()
                    .id(UUID.randomUUID())
                    .userData(userData)
                    .build();
            adminRepository.save(admin);
        }
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

            var profileInfo = Arrays.asList(
                    new Paragraph(new ParagraphDto("Paragraph1","Content1Content1Content1Content1"),0),
                    new Paragraph(new ParagraphDto("Paragraph2","Content2Content1Content2Content2"),1),
                    new Paragraph(new ParagraphDto("Paragraph3","Content3Content3Content1Content3"),2)
            );

            var teacher = Teacher.builder()
                    .id(UUID.randomUUID())
                    .userData(userData)
                    .city("Białystok")
                    .profileInfo(profileInfo)
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
                    .id(UUID.fromString("c5a6311b-e164-4633-ac7f-849e4bd84de7"))
                    .firstName("teacherFirstName3")
                    .lastName("teacherLastName3")
                    .emailAddress("teacher3@op.pl")
                    .password(passwordEncoder.encode("password"))
                    .role("TEACHER")
                    .build();
            var teacher3 = Teacher.builder()
                    .id(UUID.fromString("c5a6311b-e164-4633-ac7f-849e4bd84de8"))
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
