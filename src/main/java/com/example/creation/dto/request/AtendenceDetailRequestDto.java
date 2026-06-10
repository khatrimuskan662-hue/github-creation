package com.example.creation.dto.request;

import com.example.creation.entity.AttendenceStatus;

public record AtendenceDetailRequestDto(

        Integer studentId,

        AttendenceStatus status
) {
}
