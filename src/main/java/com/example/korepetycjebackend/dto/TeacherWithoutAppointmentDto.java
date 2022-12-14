package com.example.korepetycjebackend.dto;

import com.example.korepetycjebackend.models.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherWithoutAppointmentDto {
    private UUID id;
    private UserData userData;
    private String city;
}
