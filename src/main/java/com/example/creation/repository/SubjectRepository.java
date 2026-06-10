package com.example.creation.repository;

import com.example.creation.entity.SemesterEntity;
import com.example.creation.entity.SubjectEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity ,Integer> {
    Optional<SubjectEntity>
    findBySubjectNameAndSemester_Id(
            String subjectName,
            SemesterEntity semester
    );

    boolean existsBySubjectNameAndSemester_Id(
            String subjectName,
            @NotNull(message = "Semester id is required")
            @Min(1) int semester
    );
}
