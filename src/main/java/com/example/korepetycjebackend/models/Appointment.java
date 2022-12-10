package com.example.korepetycjebackend.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class Appointment {
    @Id
    private UUID id;
    private LocalDateTime date;

    @ManyToOne
    private Subject subject;

}
