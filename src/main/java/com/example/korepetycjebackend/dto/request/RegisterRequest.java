package com.example.korepetycjebackend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private AddressRegisterRequest address;
}
