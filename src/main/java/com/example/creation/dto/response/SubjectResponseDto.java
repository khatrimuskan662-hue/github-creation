package com.example.creation.dto.response;

public record SubjectResponseDto(
        int id,

        String subjectName,

        int semesterId,

        String semesterName
) {
}
