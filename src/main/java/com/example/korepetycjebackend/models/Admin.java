package com.example.korepetycjebackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Getter @Setter
public class Admin{
    @Id
    private UUID id;

    @OneToOne
    private UserData userData;
}
