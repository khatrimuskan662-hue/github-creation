package com.example.creation.service;

import com.example.creation.dto.request.TeacherAssignRequestDto;
import com.example.creation.dto.response.TeacherAssignResponseDto;

import java.util.List;

public interface TeacherAssignService {
    TeacherAssignResponseDto assignTeacher(
            TeacherAssignRequestDto requestDto
    );
    List<TeacherAssignResponseDto> getAllAssignments();

    TeacherAssignResponseDto getAssignmentById(
            int id
    );

    void deleteAssignment(
            int id
    );
}
