package com.example.korepetycjebackend.dto;

import com.example.korepetycjebackend.models.Appointment;
import com.example.korepetycjebackend.models.UserData;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private UUID id;
    private UserData userData;
    private List<AppointmentDto> appointments;
}
