package com.example.creation.repository;

import com.example.creation.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
    boolean existsByRollNumber(String rollNumber);
}
