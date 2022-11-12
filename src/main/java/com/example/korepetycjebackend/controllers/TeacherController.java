package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Teacher;
import com.example.korepetycjebackend.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers(){
        return teacherService.getAll();
    }

    @PostMapping("/teacher")
    public UUID createTeacher(@RequestBody RegisterRequest request){
        return teacherService.createTeacher(request);
    }

    @PostMapping("/teacher/{teacherId}/subjects")
    public void addSubjects(@PathVariable UUID teacherId, @RequestBody List<String> subjectStrings){
        teacherService.addSubjects(teacherId,subjectStrings);
    }
}
