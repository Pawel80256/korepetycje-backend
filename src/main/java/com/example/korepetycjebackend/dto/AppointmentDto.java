package com.example.korepetycjebackend.dto;

import com.example.korepetycjebackend.models.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private UUID id;
    private LocalDateTime date;
    private Subject subject;
    private TeacherWithoutAppointmentDto teacher;
    private ClientUserDataDto client;
}
