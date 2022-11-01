package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, String> {
 boolean existsByEmailAddress(String emailAddress);
 Optional<UserData> findByEmailAddress(String emailAddress);
}
