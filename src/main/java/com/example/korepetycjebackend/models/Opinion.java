package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.dto.request.CreateOpinionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Opinion {
    @Id
    private UUID id;
    private String text;
    private Integer numericValue;
    private LocalDate createdAt;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Client client;
}
