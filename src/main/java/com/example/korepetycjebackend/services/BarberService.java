package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import com.example.korepetycjebackend.models.Barber;
import com.example.korepetycjebackend.models.UserData;
import com.example.korepetycjebackend.repositories.BarberRepository;
import com.example.korepetycjebackend.repositories.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BarberService {
    private final BarberRepository barberRepository;
    private final UserDataRepository userDataRepository;
    public List<Barber> getAll(){
        return barberRepository.findAll();
    }

    public UUID createBarber(RegisterRequest registerRequest){

        if(userDataRepository.existsByEmailAddress(registerRequest.getEmailAddress())){
            throw new RuntimeException("email taken");
        }

        var barber = new Barber(registerRequest);

        barberRepository.save(barber);
        return barber.getId();
    }
}
