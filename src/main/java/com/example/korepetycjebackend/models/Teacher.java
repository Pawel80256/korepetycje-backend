package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.constants.Role;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter
public class Teacher {
    @Id
    private UUID id;

    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    private UserData userData;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paragraph> profileInfo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opinion> opinions;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Appointment> appointments;


    public Teacher(RegisterRequest registerRequest) {
        this.id = UUID.randomUUID();
        this.userData = new UserData(registerRequest, Role.TEACHER.toString());
        this.city = registerRequest.getCity();
    }
}
