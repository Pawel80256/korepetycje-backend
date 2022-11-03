package com.example.korepetycjebackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Subject {
    @Id
    private String subjectName;
}
