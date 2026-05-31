package com.example.creation.repository;

import com.example.creation.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity ,Integer> {
    boolean existsBySubjectNameAndSemester_id(
            String subjectName,
            int semesterId
    );
}
