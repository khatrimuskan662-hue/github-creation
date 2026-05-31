package com.example.creation.repository;

import com.example.creation.entity.SemesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<SemesterEntity,Integer> {
boolean existsBySemesterNameAndFaculty_id(String semesterName,int facultyId);
}
