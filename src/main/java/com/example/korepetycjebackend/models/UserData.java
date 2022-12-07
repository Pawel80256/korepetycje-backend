package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserData {
    @Id
    protected UUID id;
    protected String emailAddress;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String role;

    public UserData(RegisterRequest request, String role){
        this.id = UUID.randomUUID();
        this.emailAddress = request.getEmailAddress();
        this.password = request.getPassword();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.role = role;
    }
}
