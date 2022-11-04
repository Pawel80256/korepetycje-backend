package com.example.korepetycjebackend.models;

import com.example.korepetycjebackend.dto.request.AddressRegisterRequest;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Address {
    @Id
    private UUID id;
    private String city;
    private String roadName;
    private String roadNumber;
    private String localNumber;

    public Address(AddressRegisterRequest addressRegisterRequest){
        this.id = UUID.randomUUID();
        this.city = addressRegisterRequest.getCity();
        this.roadName = addressRegisterRequest.getRoadName();
        this.roadNumber = addressRegisterRequest.getRoadNumber();
        this.localNumber = addressRegisterRequest.getLocalNumber();
    }
}
