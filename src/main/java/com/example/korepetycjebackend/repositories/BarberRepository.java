package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.Admin;
import com.example.korepetycjebackend.models.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BarberRepository extends JpaRepository<Barber, UUID> {
//     boolean existsByEmailAddress(String emailAddress);
//     Barber findByEmailAddress(String emailAddress);
}
