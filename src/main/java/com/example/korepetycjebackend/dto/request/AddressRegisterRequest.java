package com.example.korepetycjebackend.dto.request;

import lombok.Getter;

@Getter
public class AddressRegisterRequest {
    private String city;
    private String roadName;
    private String roadNumber;
    private String localNumber;
}
