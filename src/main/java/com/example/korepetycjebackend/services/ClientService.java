package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Client;
import com.example.korepetycjebackend.models.UserData;
import com.example.korepetycjebackend.repositories.ClientRepository;
import com.example.korepetycjebackend.repositories.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserDataRepository userDataRepository;
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public UUID createClient(RegisterRequest request){
        var userData = new UserData(request);
        var client = new Client(userData);
        //todo: probably find better solution in future
        userDataRepository.save(userData);
        clientRepository.save(client);
        return client.getId();
    }
}
