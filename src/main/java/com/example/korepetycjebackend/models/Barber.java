package com.example.korepetycjebackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Barber {
    @Id
    private UUID id;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
}
