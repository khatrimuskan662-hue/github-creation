package com.example.creation.repository;

import com.example.creation.entity.FacultyEntity;
import com.example.creation.entity.SemesterEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemesterRepository extends JpaRepository<SemesterEntity,Integer> {
    Optional<SemesterEntity>
    findBySemesterNameAndFaculty(
            String semesterName,
            FacultyEntity faculty
    );

    boolean existsBySemesterNameAndFaculty_Id(
            String semesterName,

            @NotNull(message = "Faculty id is required")
            @Min(1) int facultyId
    );

}
