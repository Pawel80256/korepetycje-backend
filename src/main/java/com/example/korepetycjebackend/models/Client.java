package com.example.korepetycjebackend.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Client {
    @Id
    private UUID id;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
}
