package com.example.creation.repository;

import com.example.creation.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherassignmentEntitys,Integer> {
    boolean existsByTeacherAndSubjectAndSemester(
            TeacherEntity teacher,
            SubjectEntity subject,
            SemesterEntity semester
    );
}
