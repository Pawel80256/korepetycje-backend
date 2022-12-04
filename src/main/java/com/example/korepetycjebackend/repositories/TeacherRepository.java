package com.example.korepetycjebackend.repositories;

import com.example.korepetycjebackend.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    @Query(value = "SELECT * FROM teacher t " +
            "WHERE t.id IN " +
            "(SELECT ts.teacher_id FROM teacher_subjects ts" +
            " WHERE ts.subjects_subject_name = :subject)", nativeQuery = true)
    List<Teacher> findBySubject(String subject);

    List<Teacher> findByCity(String city);

}
