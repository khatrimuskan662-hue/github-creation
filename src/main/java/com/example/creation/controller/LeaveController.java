package com.example.creation.controller;

import com.example.creation.dto.request.LeaveRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.LeaveResponseDto;
import com.example.creation.service.Leaveservice;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leave")
public class LeaveController {
    private final Leaveservice leaveservice;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponse<LeaveResponseDto>> applyLeavve(
            @Valid @RequestBody LeaveRequestDto dto
            ){
        LeaveResponseDto responseDto=leaveservice.applyLeave(dto);
        ApiResponse<LeaveResponseDto> response=
                new ApiResponse<>(
                        true,
                        "leave sucessfully applied",
                        responseDto
                );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<LeaveResponseDto>>> getAllLeaves(){
        List<LeaveResponseDto> responseDtos=leaveservice.getAllLeaves();
        ApiResponse<List<LeaveResponseDto>> response=
                new ApiResponse<>(
                        true,
                        "fetched all applied leave",
                        responseDtos
                );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<LeaveResponseDto>> approve(
            @PathVariable Integer id
    ){
        LeaveResponseDto leaveApprove=leaveservice.approveLeave(id);
        ApiResponse<LeaveResponseDto> response=
                new ApiResponse<>(
                        true,
                        "leave approved",
                        leaveApprove
                );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/reject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<LeaveResponseDto>> reject(
            @PathVariable Integer id
    ){
        LeaveResponseDto leaveReject=leaveservice.rejectLeave(id);
        ApiResponse<LeaveResponseDto> response=
                new ApiResponse<>(
                        true,
                        "leave rejected",
                        leaveReject
                );
        return ResponseEntity.ok(response);
    }
}
