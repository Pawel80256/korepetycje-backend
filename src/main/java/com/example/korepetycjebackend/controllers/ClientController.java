package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Client;
import com.example.korepetycjebackend.models.UserData;
import com.example.korepetycjebackend.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/api/clients")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping("/api/client")
    public UUID createClient(@RequestBody RegisterRequest request){
        return clientService.createClient(request);
    }
}
