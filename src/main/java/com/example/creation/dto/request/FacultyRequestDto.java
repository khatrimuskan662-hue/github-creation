package com.example.creation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FacultyRequestDto(
        @NotBlank(message = "Faculty name is required")
        String facultyName
) {
}
