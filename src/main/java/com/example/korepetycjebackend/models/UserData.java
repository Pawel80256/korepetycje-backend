package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.dto.request.RegisterRequest;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.UUID;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserData {
    @Id
    protected String emailAddress;
    protected String password;
    protected String firstName;
    protected String lastName;

    public UserData(RegisterRequest request){
        this.emailAddress = request.getEmailAddress();
        this.password = request.getPassword();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
    }
}
