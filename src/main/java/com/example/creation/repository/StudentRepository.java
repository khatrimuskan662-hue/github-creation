package com.example.creation.repository;

import com.example.creation.entity.FacultyEntity;
import com.example.creation.entity.SemesterEntity;
import com.example.creation.entity.StudentEntity;
import com.example.creation.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
    boolean existsByFacultyAndSemesterAndSubject(

             FacultyEntity faculty,
             SemesterEntity semester,
             SubjectEntity subject
    );
}
