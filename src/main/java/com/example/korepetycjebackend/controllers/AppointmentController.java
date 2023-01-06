package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.AppointmentDto;
import com.example.korepetycjebackend.dto.request.CreateAppointmentRequest;
import com.example.korepetycjebackend.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public List<AppointmentDto> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @PostMapping("/appointment")
    public UUID createAppointment(@RequestBody CreateAppointmentRequest request){
        return appointmentService.createAppointment(request);
    }

    @PutMapping("/appointment/{appointmentId}/book")
    public void bookAppointment(@PathVariable UUID appointmentId, @RequestParam UUID clientId, @RequestParam String subjectName){
        appointmentService.bookAppointment(appointmentId,clientId,subjectName);
    }

    @PutMapping("/appointment/{appointmentId}/cancel")
    public void cancelBooking(@PathVariable UUID appointmentId, @RequestParam UUID clientId){
        appointmentService.cancelBooking(appointmentId,clientId);
    }

    @DeleteMapping("/appointment/{appointmentId}")
    public void deleteAppointment(@PathVariable UUID appointmentId){
        appointmentService.deleteAppointment(appointmentId);
    }
}
