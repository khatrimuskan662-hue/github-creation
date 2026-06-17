package com.example.creation.dto.response;

import java.time.LocalDateTime;

public record SemesterResponseDto(
        int id,

        String semesterName,

        int facultyId,

        String facultyName
) {
}
