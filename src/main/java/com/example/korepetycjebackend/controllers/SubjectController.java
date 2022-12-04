package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.models.Subject;
import com.example.korepetycjebackend.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectRepository subjectRepository;

    @GetMapping("/subjects")
    public ResponseEntity<List<String>> getAllSubjectNames(){
        var subjects = subjectRepository.findAll();
        var subjectNames = subjects.stream().map(s -> s.getSubjectName()).collect(Collectors.toList());
        return ResponseEntity.ok(subjectNames);
    }
}
