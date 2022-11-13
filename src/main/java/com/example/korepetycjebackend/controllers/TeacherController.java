package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.AddToProfileInfoRequest;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.dto.request.UpdateParagraphRequest;
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

    @GetMapping("teachersBySubject")
    public List<Teacher> getBySubject(@RequestParam String subject){
        return teacherService.getBySubject(subject);
    }

    @PostMapping("/teacher")
    public UUID createTeacher(@RequestBody RegisterRequest request){
        return teacherService.createTeacher(request);
    }

    @PostMapping("/teacher/{teacherId}/subjects")
    public void addSubjects(@PathVariable UUID teacherId, @RequestBody List<String> subjectStrings){
        teacherService.addSubjects(teacherId,subjectStrings);
    }

    @PostMapping("/teacher/{teacherId}/profileInfo")
    public void addToProfileInfo(@PathVariable UUID teacherId, @RequestBody AddToProfileInfoRequest request){
        teacherService.addToProfileInfo(teacherId,request);
    }

    @PutMapping("/teacher/paragraph/{paragraphId}")
    public void updateProfileInfo(@PathVariable UUID paragraphId, @RequestBody UpdateParagraphRequest request){
        teacherService.updateProfileInfo(paragraphId,request);
    }

    @PutMapping("/teacher/{teacherId}/paragraph/{paragraphId}/order")
    public void changeParagraphOrder(@PathVariable UUID teacherId, @PathVariable UUID paragraphId, @RequestParam boolean orderUp){
        teacherService.changeParagraphOrder(teacherId,paragraphId,orderUp);
    }

    @DeleteMapping("/teacher/{teacherId}/profileInfo/{paragraphId}")
    public void deleteFromProfileInfo(@PathVariable UUID teacherId, @PathVariable UUID paragraphId){
        teacherService.deleteFromProfileInfo(teacherId,paragraphId);
    }

}
