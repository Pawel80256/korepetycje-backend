package com.example.korepetycjebackend.dto;

import com.example.korepetycjebackend.models.Opinion;
import com.example.korepetycjebackend.models.Paragraph;
import com.example.korepetycjebackend.models.Subject;
import com.example.korepetycjebackend.models.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private UUID id;
    private String city;
    private UserData userData;
    private List<Subject> subjects;
    private List<Paragraph> profileInfo;
    private List<Opinion> opinions;
    private List<AppointmentDto> appointments;
}
