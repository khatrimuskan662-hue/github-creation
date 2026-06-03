package com.example.creation.repository;

import com.example.creation.entity.CalenderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderRepository extends JpaRepository<CalenderEvent,Integer> {
}
