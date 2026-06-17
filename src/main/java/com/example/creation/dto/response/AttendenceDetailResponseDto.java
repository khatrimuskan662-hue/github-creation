package com.example.creation.dto.response;

import java.time.LocalDateTime;

public record AttendenceDetailResponseDto(

        Integer studentId,

        String studentName,

        String status
) {
}
