package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.OpinionDto;
import com.example.korepetycjebackend.dto.request.CreateOpinionRequest;
import com.example.korepetycjebackend.services.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OpinionController {
    private final OpinionService opinionService;

    @PostMapping("/opinion")
    public UUID createOpinion(@RequestBody CreateOpinionRequest request){
        return opinionService.createOpinion(request);
    }

    @GetMapping("/opinions")
    public List<OpinionDto> getAllOpinions(){
        return opinionService.getAllOpinions();
    }

}
