package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.Admin;
import com.example.korepetycjebackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
//    boolean existsByEmailAddress(String emailAddress);
    @Query("SELECT c FROM Client c WHERE  c.userData.emailAddress = :emailAddress")
    Optional<Client> findByEmailAddress(String emailAddress);

}
