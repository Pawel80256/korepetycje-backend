package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.CreateAppointmentRequest;
import com.example.korepetycjebackend.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/appointment")
    public UUID createAppointment(@RequestBody CreateAppointmentRequest request){
        return appointmentService.createAppointment(request);
    }

    @PutMapping("/appointment/{appointmentId}/book")
    public void bookAppointment(@PathVariable UUID appointmentId, @RequestParam UUID userDataId, @RequestParam String subjectName){
        appointmentService.bookAppointment(appointmentId,userDataId,subjectName);
    }

    @DeleteMapping("/appointment/{appointmentId}")
    public void deleteAppointment(@PathVariable UUID appointmentId){
        appointmentService.deleteAppointment(appointmentId);
    }
}
