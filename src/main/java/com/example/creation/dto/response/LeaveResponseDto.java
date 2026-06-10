package com.example.creation.dto.response;

import com.example.creation.entity.LeaveStatus;
import com.example.creation.entity.LeaveType;

import java.time.LocalDate;

public record LeaveResponseDto(
        Integer id,

        String teacherName,

        LocalDate fromDate,

        LocalDate toDate,

        LeaveType leaveType,

        String reason,

        LeaveStatus status
) {
}
