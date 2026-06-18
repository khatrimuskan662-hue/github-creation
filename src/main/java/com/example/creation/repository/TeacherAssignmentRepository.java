package com.example.creation.repository;

import com.example.creation.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherassignmentEntity,Integer> {
    boolean existsByTeacher_IdAndSubject_IdAndSemester_Id(
            int teacherId,
            int subjectId,
            int semesterId
    );
}
