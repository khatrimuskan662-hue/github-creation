package com.example.creation.dto.response;

public record AttendencePercenteageDto(
        Integer studentId,

        String studentName,

        long totalClasses,

        long presentClasses,

        double percentage
) {
}
