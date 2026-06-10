package com.example.creation.dto.request;

import com.example.creation.entity.LeaveType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LeaveRequestDto(
        @NotNull
        Integer teacherId,

        @NotNull
        LocalDate fromDate,

        @NotNull
        LocalDate toDate,

        @NotNull
        LeaveType leaveType,

        String reason
) {
}
