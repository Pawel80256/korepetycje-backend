package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
}
