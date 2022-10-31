package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Barber;
import com.example.korepetycjebackend.services.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BarberController {
    private final BarberService barberService;

    @GetMapping("/api/barbers")
    public List<Barber> getAllBarbers(){
        return barberService.getAll();
    }

    @PostMapping("/api/barber")
    public UUID createBarber(@RequestBody RegisterRequest request){
        return barberService.createBarber(request);
    }
}
