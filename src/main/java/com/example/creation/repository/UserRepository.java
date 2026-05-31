package com.example.creation.repository;

import com.example.creation.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity ,Integer> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(
            String email
    );
}
