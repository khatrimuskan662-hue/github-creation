package com.example.creation.repository;

import com.example.creation.entity.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendenceRepository extends JpaRepository<Attendence,Integer> {
}
