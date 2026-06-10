package com.example.creation.service;

import com.example.creation.dto.request.AttendenceRequestDto;
import com.example.creation.dto.response.AttendencePercenteageDto;
import com.example.creation.dto.response.AttendenceResponseDto;

import java.util.List;

public interface AttendenceService {
    AttendenceResponseDto createAttendance(
            AttendenceRequestDto dto
    );

    List<AttendenceResponseDto> getAllAttendance();

    AttendenceResponseDto getAttendanceById(
            Integer id
    );
    AttendencePercenteageDto getAttendancePercentage(
            int studentId
    );
}
