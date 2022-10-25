package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.RegisterClientRequest;
import com.example.korepetycjebackend.models.Client;
import com.example.korepetycjebackend.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public UUID createClient(RegisterClientRequest request){
        var client = Client.builder()
                .id(UUID.randomUUID())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailAddress(request.getEmailAddress())
                .password(request.getPassword())
                .build();
        clientRepository.save(client);
        return client.getId();
    }
}
