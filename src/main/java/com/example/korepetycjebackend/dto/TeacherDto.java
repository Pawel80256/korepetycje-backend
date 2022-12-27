package com.example.korepetycjebackend.dto;

import com.example.korepetycjebackend.models.Opinion;
import com.example.korepetycjebackend.models.Paragraph;
import com.example.korepetycjebackend.models.Subject;
import com.example.korepetycjebackend.models.UserData;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDto {
    private UUID id;
    private String city;
    private UserData userData;
    private List<Subject> subjects;
    private List<Paragraph> profileInfo;
    private List<OpinionDto> opinions;
    private List<AppointmentDto> appointments;
}
