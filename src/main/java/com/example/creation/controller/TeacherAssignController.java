package com.example.creation.controller;

import com.example.creation.dto.request.TeacherAssignRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.TeacherAssignResponseDto;
import com.example.creation.service.TeacherAssignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher-assign")
@RequiredArgsConstructor
public class TeacherAssignController {
    private final TeacherAssignService teacherAssignService;


    @PostMapping
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
}
