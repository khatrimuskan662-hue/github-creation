package com.example.creation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestDto(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Duration is required")
        String duration,

        int teacherId
) {
}
