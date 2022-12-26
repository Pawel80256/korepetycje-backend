package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.AppointmentDto;
import com.example.korepetycjebackend.dto.ClientDto;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Appointment;
import com.example.korepetycjebackend.models.Client;
import com.example.korepetycjebackend.models.UserData;
import com.example.korepetycjebackend.services.AppointmentService;
import com.example.korepetycjebackend.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final AppointmentService appointmentService;

    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/client/{clientId}")
    public ClientDto getClientById(@PathVariable UUID clientId){
        return clientService.getClientById(clientId);
    }

    @GetMapping("/client/{clientId}/appointments")
    public List<AppointmentDto> getAppointmentsByClientId(@PathVariable UUID clientId){
        return clientService.getAppointmentsByClientId(clientId);
    }

    @PostMapping("/client")
    public UUID createClient(@RequestBody RegisterRequest request){
        return clientService.createClient(request);
    }
}
