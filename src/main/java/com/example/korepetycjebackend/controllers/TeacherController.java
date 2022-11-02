package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Teacher;
import com.example.korepetycjebackend.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> getAllBarbers(){
        return teacherService.getAll();
    }

    @PostMapping("/teacher")
    public UUID createBarber(@RequestBody RegisterRequest request){
        return teacherService.createBarber(request);
    }
}
