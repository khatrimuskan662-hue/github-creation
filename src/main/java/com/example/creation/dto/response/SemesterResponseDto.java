package com.example.creation.dto.response;

public record SemesterResponseDto(
        int id,

        String semesterName,

        int facultyId,

        String facultyName
) {
}
