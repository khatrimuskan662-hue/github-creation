package com.example.creation.controller;

import com.example.creation.dto.request.AttendenceRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.AttendencePercenteageDto;
import com.example.creation.dto.response.AttendenceResponseDto;
import com.example.creation.service.AttendenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendence")
@RequiredArgsConstructor
public class AttendenceController {

    private final AttendenceService attendenceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<AttendenceResponseDto>>
    createAttendence(@Valid @RequestBody AttendenceRequestDto dto) {
        AttendenceResponseDto responseDto = attendenceService.createAttendance(dto);
        ApiResponse<AttendenceResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Attendence Created",
                        responseDto
                );
        return ResponseEntity.ok(response);

    }


    @GetMapping("/percentage/{studentId}")
    public ResponseEntity<ApiResponse<AttendencePercenteageDto>> getPercentage(
            @PathVariable int studentId
    ) {
        AttendencePercenteageDto percenteageDto = attendenceService.getAttendancePercentage(studentId);
        ApiResponse<AttendencePercenteageDto> response =
                new ApiResponse<>(
                        true,
                        "attendence by percentage",
                        percenteageDto
                );
        return ResponseEntity.ok(response);
    }
}