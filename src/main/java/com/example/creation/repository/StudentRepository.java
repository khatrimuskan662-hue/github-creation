package com.example.creation.repository;

import com.example.creation.entity.FacultyEntity;
import com.example.creation.entity.SemesterEntity;
import com.example.creation.entity.StudentEntity;
import com.example.creation.entity.SubjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {

    List<StudentEntity>
    findByUser_NameContainingIgnoreCase(
            String keyword
    );
}
