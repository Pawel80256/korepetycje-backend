package com.example.korepetycjebackend.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class CreateOpinionRequest {
    private UUID clientId;
    private UUID teacherId;
    private String Text;
    private Integer numericValue;
    private LocalDate createdAt;
}
