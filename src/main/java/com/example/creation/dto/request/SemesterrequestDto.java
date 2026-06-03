package com.example.creation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SemesterrequestDto(
        @NotBlank(message = "Semester name is required")
        String semesterName,

        @NotNull(message = "Faculty id is required")
        @Min(1)
        int facultyId
) {
}
