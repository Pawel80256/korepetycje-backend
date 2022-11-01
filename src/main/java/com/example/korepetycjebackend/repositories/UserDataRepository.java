package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, String> {
 boolean existsByEmailAddress(String emailAddress);
}
