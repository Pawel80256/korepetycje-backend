package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.AddToProfileInfoRequest;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.dto.request.UpdateParagraphRequest;
import com.example.korepetycjebackend.models.Subject;
import com.example.korepetycjebackend.models.Teacher;
import com.example.korepetycjebackend.services.AppointmentService;
import com.example.korepetycjebackend.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final AppointmentService appointmentService;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers(){
        return teacherService.getAll();
    }

    @GetMapping("/teacher/{teacherId}")
    public Teacher getById(@PathVariable UUID teacherId){
        return teacherService.getById(teacherId);
    }

    @GetMapping("/teachersBySubject")
    public List<Teacher> getBySubject(@RequestParam String subject){
        return teacherService.getBySubject(subject);
    }

    @GetMapping("/teachersBySubjectAndCity")
    List<Teacher> getBySubjectAndCity(@RequestParam String subject, @RequestParam String city){
        return teacherService.getAllBySubjectAndCity(subject,city);
    }

    @GetMapping("/teacher/{teacherId}/subjects")
    List<Subject> getSubjectsByTeacherId(@PathVariable UUID teacherId){
        return teacherService.getSubjectsByTeacherId(teacherId);
    }

//    @GetMapping("/teacher/{teacherId}/appointments")
//    public List<Appointment> getAppointmentsByTeacherId(@PathVariable UUID teacherId){
//        return appointmentService.getByTeacherId(teacherId);
//    }

    @GetMapping("/teacher/cities")
    public List<String> getAllCities(){
        var teachers = teacherService.getAll();
        var cities = teachers.stream().map(t -> t.getCity()).collect(Collectors.toList());
        return cities;  
    }

    @PostMapping("/teacher")
    public UUID createTeacher(@RequestBody RegisterRequest request){
        return teacherService.createTeacher(request);
    }

    @PostMapping("/teacher/{teacherId}/subject")
    public void addSubject(@PathVariable UUID teacherId, @RequestParam String subjectName){
        teacherService.addSubject(teacherId,subjectName);
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

    @DeleteMapping("/teacher/{teacherId}/subject")
    public void deleteSubject(@PathVariable UUID teacherId, @RequestParam String subjectName){
        teacherService.deleteSubject(teacherId,subjectName);
    }

}
