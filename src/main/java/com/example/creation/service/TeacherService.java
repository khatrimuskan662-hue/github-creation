package com.example.creation.service;

import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.TeacherResponseDto;
import com.example.creation.entity.TeacherEntity;

import java.util.List;

public interface TeacherService {

    public TeacherResponseDto saveTeacher(TeacherRequestDto teacherRequestDto);
    public List<TeacherResponseDto> teacherList();
    public String deleteTeacher(int id);
    public TeacherResponseDto updateTeacher(int id,TeacherRequestDto teacherRequestDto);
}
