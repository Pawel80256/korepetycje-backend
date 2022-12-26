package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.AppointmentDto;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Appointment;
import com.example.korepetycjebackend.models.Client;
import com.example.korepetycjebackend.models.UserData;
import com.example.korepetycjebackend.repositories.ClientRepository;
import com.example.korepetycjebackend.repositories.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public UUID createClient(RegisterRequest registerRequest){

        if(userDataRepository.existsByEmailAddress(registerRequest.getEmailAddress())){
            throw new RuntimeException("email taken");
        }

        registerRequest.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );

        var client = new Client(registerRequest);

        clientRepository.save(client);
        return client.getId();
    }

    public List<AppointmentDto> getAppointmentsByClientId(UUID clientId){
        var client = clientRepository.findById(clientId)
                .orElseThrow(()->new RuntimeException("client not found"));
        var appointments = client.getAppointments();
        return appointments.stream()
                .map(a ->modelMapper.map(a, AppointmentDto.class))
                .collect(Collectors.toList());
    }
}
