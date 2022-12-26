package com.example.korepetycjebackend.dto;

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
    private String firstName;
    private String lastName;
    private String city;
}
