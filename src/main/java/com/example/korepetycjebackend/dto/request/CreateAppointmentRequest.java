package com.example.korepetycjebackend.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CreateAppointmentRequest {
    private UUID teacherId;
    private LocalDateTime date;
}
