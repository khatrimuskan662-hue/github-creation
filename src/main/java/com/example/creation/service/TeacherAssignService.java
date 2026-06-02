package com.example.creation.service;

import com.example.creation.dto.request.TeacherAssignRequestDto;
import com.example.creation.dto.response.TeacherAssignResponseDto;

public interface TeacherAssignService {
    TeacherAssignResponseDto assignTeacher(
            TeacherAssignRequestDto requestDto
    );
}
