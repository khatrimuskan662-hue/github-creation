package com.example.creation.repository;

import com.example.creation.entity.AttendanceDetailEntity;
import com.example.creation.entity.AttendenceStatus;
import com.example.creation.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendenceDetailRepository extends JpaRepository<AttendanceDetailEntity,Integer> {
    List<AttendanceDetailEntity>
    findByStudent(StudentEntity student);
    long countByStudent(StudentEntity student);

    long countByStudentAndStatus(
            StudentEntity student,
            AttendenceStatus status
    );
}
