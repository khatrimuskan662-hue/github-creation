package com.example.creation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StudentRequestDto(

        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        @NotNull(message = "Faculty id is required")
        Integer facultyId,

        @NotNull(message = "Semester id is required")
        Integer semesterId,

        List<Integer>subjectIds,

        String photoPath
) {
}
