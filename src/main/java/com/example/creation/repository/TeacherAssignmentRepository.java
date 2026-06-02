package com.example.creation.repository;

import com.example.creation.entity.SemesterEntity;
import com.example.creation.entity.SubjectEntity;
import com.example.creation.entity.TeacherEntity;
import com.example.creation.entity.TeacherassignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherassignmentEntity,Integer> {
    boolean existsByTeacherAndSubjectAndSemester(
            TeacherEntity teacher,
            SubjectEntity subject,
            SemesterEntity semester
    );
}
