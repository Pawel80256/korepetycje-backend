package com.example.korepetycjebackend.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String emailAddress;
    private String password;
}
