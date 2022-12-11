package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.constants.Role;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class Client{
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserData userData;

    @OneToMany
    private List<Appointment> appointments;

    public Client(RegisterRequest registerRequest) {
        this.id = UUID.randomUUID();
        this.userData = new UserData(registerRequest, Role.CLIENT.toString());
    }
}
