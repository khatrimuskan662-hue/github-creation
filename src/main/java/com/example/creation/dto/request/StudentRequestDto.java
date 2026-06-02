package com.example.creation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequestDto(
        //@NotBlank(message = "Name is required")
        //String name,

        //@Email(message = "Invalid email")
        //String email,

        //@NotBlank(message = "Password is required")
        //String password,

        //@NotBlank(message = "Roll number is required")
        //String rollNumber,

        @NotNull(message = "user id is required")
        int userId,

        @NotNull(message = "Faculty id is required")
        int facultyId,

        @NotNull(message = "Semester id is required")
        int semesterId,

        @NotNull(message = "subject is required")
        int subjectId
) {
}
