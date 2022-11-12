package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.constants.Role;
import com.example.korepetycjebackend.dto.request.RegisterRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Teacher {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserData userData;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Subject> subjects;


    public Teacher(RegisterRequest registerRequest) {
        this.id = UUID.randomUUID();
        this.userData = new UserData(registerRequest, Role.TEACHER.toString());
    }
}
