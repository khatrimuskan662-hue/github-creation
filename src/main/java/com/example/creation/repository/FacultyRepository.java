package com.example.creation.repository;

import com.example.creation.entity.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<FacultyEntity,Integer> {
    Optional<FacultyEntity> findByFacultyName(String facultyName);
    boolean existsByFacultyName(String facultyName);
}
