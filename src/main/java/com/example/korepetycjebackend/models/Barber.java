package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.constants.Role;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Barber {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserData userData;

    public Barber(RegisterRequest registerRequest) {
        this.id = UUID.randomUUID();
        this.userData = new UserData(registerRequest, Role.BARBER.toString());
    }
}
