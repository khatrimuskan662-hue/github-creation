package com.example.creation.dto.response;

public record SemesterResponseDto(
        int id,

        String semesterName,

        Long facultyId,

        String facultyName
) {
}
