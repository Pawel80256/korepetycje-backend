package com.example.korepetycjebackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
public class Admin {
    @Id
    private UUID id;
    private String emailAddress;
    private String password;
}
