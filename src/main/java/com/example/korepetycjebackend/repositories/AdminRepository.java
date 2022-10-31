package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
//    boolean existsByEmailAddress(String emailAddress);
//    Admin findByEmailAddress(String emailAddress);
}
