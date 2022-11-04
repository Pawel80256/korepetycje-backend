package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Address;
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

    public UUID createBarber(RegisterRequest registerRequest){

        if(userDataRepository.existsByEmailAddress(registerRequest.getEmailAddress())){
            throw new RuntimeException("email taken");
        }

        var teacher = new Teacher(registerRequest);

        if(registerRequest.getAddress() != null){
            var address = new Address(registerRequest.getAddress());
            teacher.setAddress(address);
        }

        registerRequest.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );

        teacherRepository.save(teacher);
        return teacher.getId();
    }
}
