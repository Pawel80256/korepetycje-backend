package com.example.korepetycjebackend.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Admin{
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserData userData;
}
