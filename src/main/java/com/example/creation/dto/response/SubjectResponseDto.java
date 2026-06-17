package com.example.creation.dto.response;

import java.time.LocalDateTime;

public record SubjectResponseDto(
        int id,

        String subjectName,

        int semesterId,

        String semesterName
) {
}
