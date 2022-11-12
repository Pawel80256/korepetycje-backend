package com.example.korepetycjebackend.services;

import com.example.korepetycjebackend.dto.request.CreateOpinionRequest;
import com.example.korepetycjebackend.models.Opinion;
import com.example.korepetycjebackend.repositories.ClientRepository;
import com.example.korepetycjebackend.repositories.OpinionRepository;
import com.example.korepetycjebackend.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final ClientRepository clientRepository;
    private final TeacherRepository teacherRepository;

    public UUID createOpinion(CreateOpinionRequest createOpinionRequest){
        //todo use model mapper
        var client = clientRepository.findById(createOpinionRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("client not found"));
        var teacher = teacherRepository.findById(createOpinionRequest.getTeacherId())
                .orElseThrow(() -> new RuntimeException("teacher not found"));
        var opinion = Opinion.builder()
                .id(UUID.randomUUID())
                .textValue(createOpinionRequest.getTextValue())
                .numericValue(createOpinionRequest.getNumericValue())
                .client(client)
                .teacher(teacher)
                .createdAt(LocalDate.now())
                .build();
        opinionRepository.save(opinion);
        return opinion.getId();
    }
}
