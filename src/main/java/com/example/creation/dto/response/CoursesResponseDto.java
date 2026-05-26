package com.example.creation.dto.response;

public record CoursesResponseDto(
        int id,
        String title,
        String duration,
        int teacherId,
        String teacherName
) {
}
