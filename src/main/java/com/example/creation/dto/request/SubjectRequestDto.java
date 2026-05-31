package com.example.creation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubjectRequestDto(

        @NotBlank(message = "Subject name is required")
        String subjectName,

        @NotNull(message = "Semester id is required")
        int semesterId
) {
}
