package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.CreateAppointmentRequest;
import com.example.korepetycjebackend.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/appointment")
    public UUID createAppointment(@RequestBody CreateAppointmentRequest request){
        return appointmentService.createAppointment(request);
    }

}
