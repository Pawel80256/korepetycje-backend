package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.constants.ERole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Role {
    @Id
    private ERole ERole;
}
