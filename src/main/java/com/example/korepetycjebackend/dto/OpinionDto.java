package com.example.korepetycjebackend.dto;
import com.example.korepetycjebackend.models.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpinionDto {
    private UUID id;
    private String textValue;
    private Integer numericValue;
    private LocalDate createdAt;
    private ClientUserDataDto client;
    private TeacherWithoutAppointmentDto teacher;
}
