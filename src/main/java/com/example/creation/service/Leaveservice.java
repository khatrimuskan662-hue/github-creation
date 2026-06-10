package com.example.creation.service;

import com.example.creation.dto.request.LeaveRequestDto;
import com.example.creation.dto.response.LeaveResponseDto;

import java.util.List;

public interface Leaveservice {
    LeaveResponseDto applyLeave(
            LeaveRequestDto dto
    );

    List<LeaveResponseDto> getAllLeaves();

    LeaveResponseDto approveLeave(
            Integer leaveId
    );

    LeaveResponseDto rejectLeave(
            Integer leaveId
    );
}
