package com.example.creation.repository;

import com.example.creation.entity.LeaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<LeaveEntity,Integer>
{

}
