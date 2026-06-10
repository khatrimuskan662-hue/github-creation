package com.example.creation.controller;


import com.example.creation.dto.request.SemesterrequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.SemesterResponseDto;
import com.example.creation.service.SemesterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semester")
@RequiredArgsConstructor
public class SemesterController {
    private final SemesterService semesterService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SemesterResponseDto>> createSemester(@Valid @RequestBody SemesterrequestDto semesterrequestDto){
        SemesterResponseDto semester=semesterService.createSemester(semesterrequestDto);
        ApiResponse<SemesterResponseDto> response=
                new ApiResponse<>(
                        true,
                        "semester created sucessfully",
                        semester
                );
        return ResponseEntity.ok(response);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<SemesterResponseDto>>>
    getAllSemesters() {

        List<SemesterResponseDto> semesters =
                semesterService.getAllSemesters();

        ApiResponse<List<SemesterResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Semesters fetched successfully",
                        semesters
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SemesterResponseDto>>
    getSemesterById(
            @PathVariable int id
    ) {

        SemesterResponseDto semester =
                semesterService.getSemesterById(id);

        ApiResponse<SemesterResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Semester fetched successfully",
                        semester
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SemesterResponseDto>>
    updateSemester(
            @PathVariable int id,
            @Valid @RequestBody SemesterrequestDto dto
    ) {

        SemesterResponseDto semester =
                semesterService.updateSemester(id, dto);

        ApiResponse<SemesterResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Semester updated successfully",
                        semester
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Object>>
    deleteSemester(
            @PathVariable int id
    ) {

        semesterService.deleteSemester(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        true,
                        "Semester deleted successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }
}
