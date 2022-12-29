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
import java.util.stream.Collectors;

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

        var teacherOpinions = teacher.getOpinions();

        var teacherOpinionsClientIds = teacherOpinions.stream()
                .map(opinion -> opinion.getClient().getId())
                .collect(Collectors.toList());

        if(teacherOpinionsClientIds.contains(createOpinionRequest.getClientId())){
            throw new RuntimeException("This client already gave opinion to this teacher");
        }

        var opinion = Opinion.builder()
                .id(UUID.randomUUID())
                .textValue(createOpinionRequest.getTextValue())
                .numericValue(createOpinionRequest.getNumericValue())
                .client(client)
                .createdAt(LocalDate.now())
                .build();

        opinionRepository.save(opinion);

        teacherOpinions.add(opinion);
        teacher.setOpinions(teacherOpinions);

        teacherRepository.save(teacher);
        return opinion.getId();
    }
}
