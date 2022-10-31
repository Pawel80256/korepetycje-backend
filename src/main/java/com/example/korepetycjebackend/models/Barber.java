package com.example.korepetycjebackend.models;

import lombok.*;

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

    @OneToOne
    private UserData userData;

    public Barber(UserData userData) {
        this.id = UUID.randomUUID();
        this.userData = userData;
    }
}
