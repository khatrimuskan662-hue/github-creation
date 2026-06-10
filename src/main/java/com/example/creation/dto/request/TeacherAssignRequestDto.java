package com.example.creation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TeacherAssignRequestDto(
        @NotNull(message = "Teacher id is required")
        @Min(value = 1, message = "Teacher id must be greater than 0")
        Integer teacherId,

        //@NotNull(message = "Faculty id is required")
        //@Min(value = 1, message = "Faculty id must be greater than 0")
        //Integer facultyId,

        @NotNull(message = "Subject id is required")
        @Min(value = 1, message = "Subject id must be greater than 0")
        Integer subjectId,

        @NotNull(message = "Semester id is required")
        @Min(value = 1, message = "Semester id must be greater than 0")
        Integer semesterId

) {
}
