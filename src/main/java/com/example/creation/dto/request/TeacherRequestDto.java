package com.example.creation.dto.request;

import jakarta.validation.constraints.*;

import java.util.List;

public record TeacherRequestDto(
        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Specialization is required")
        String specialization,

        @NotNull(message = "Faculty id is required")
        Integer facultyId,

        @NotEmpty(message = "Subject ids are required")
        List<Integer> subjectIds
) {
}
