package com.example.creation.controller;

import com.example.creation.dto.request.FacultyRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.FacultyResponseDto;
import com.example.creation.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FacultyResponseDto>>
    createFaculty(
            @Valid @RequestBody FacultyRequestDto dto
    ) {

        FacultyResponseDto faculty =
                facultyService.createFaculty(dto);

        ApiResponse<FacultyResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Faculty created successfully",
                        faculty
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<FacultyResponseDto>>>
    getAllFaculties() {

        List<FacultyResponseDto> faculties =
                facultyService.getAllFaculties();

        ApiResponse<List<FacultyResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Faculties fetched successfully",
                        faculties
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FacultyResponseDto>>
    getFacultyById(
            @PathVariable int id
    ) {

        FacultyResponseDto faculty =
                facultyService.getFacultyById(id);

        ApiResponse<FacultyResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Faculty fetched successfully",
                        faculty
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FacultyResponseDto>>
    updateFaculty(
            @PathVariable int id,
            @Valid @RequestBody FacultyRequestDto dto
    ) {

        FacultyResponseDto faculty =
                facultyService.updateFaculty(id, dto);

        ApiResponse<FacultyResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Faculty updated successfully",
                        faculty
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Object>>
    deleteFaculty(
            @PathVariable int id
    ) {

        facultyService.deleteFaculty(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        true,
                        "Faculty deleted successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }
}


