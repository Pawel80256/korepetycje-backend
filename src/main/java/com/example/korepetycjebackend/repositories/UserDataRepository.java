package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;
import java.util.UUID;

public interface UserDataRepository extends JpaRepository<UserData, UUID> {
 boolean existsByEmailAddress(String emailAddress);
 Optional<UserData> findByEmailAddress(String emailAddress);
}
