package com.example.creation.repository;

import com.example.creation.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity ,Integer> {
}
