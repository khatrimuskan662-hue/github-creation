package com.example.creation.controller;

import com.example.creation.dto.request.TeacherAssignRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.TeacherAssignResponseDto;
import com.example.creation.service.TeacherAssignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher-assign")
@RequiredArgsConstructor
public class TeacherAssignController {
    private final TeacherAssignService teacherAssignService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeacherAssignResponseDto>>
    assignTeacher(@Valid @RequestBody TeacherAssignRequestDto dto) {

        TeacherAssignResponseDto responseDto=
                teacherAssignService.assignTeacher(dto);

       ApiResponse<TeacherAssignResponseDto> response=
              new ApiResponse<>(
                      true,
                      "Teacher Assigned sucessfully",
                      responseDto
              );
       return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<TeacherAssignResponseDto>>>
    getAllAssign(){
        List<TeacherAssignResponseDto> responseDto=
                teacherAssignService.getAllAssignments();
        ApiResponse<List<TeacherAssignResponseDto>> response=
                new ApiResponse<>(
                        true,
                        "assignmment fetched successful",
                        responseDto
                );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeacherAssignResponseDto>>
    getAssignById(@PathVariable int id){
        TeacherAssignResponseDto responseDto=
                teacherAssignService.getAssignmentById(id);
        ApiResponse<TeacherAssignResponseDto> response=
                new ApiResponse<>(
                        true,
                        "assignment fetched with id",
                        responseDto
                );
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAssign(@PathVariable int id){
            teacherAssignService.deleteAssignment(id);
    }
    }
