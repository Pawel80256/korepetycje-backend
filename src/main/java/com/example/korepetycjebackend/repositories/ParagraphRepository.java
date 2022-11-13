package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParagraphRepository extends JpaRepository<Paragraph, UUID> {
}
