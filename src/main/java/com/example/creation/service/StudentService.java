package com.example.creation.service;

import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.dto.response.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);
    List<StudentResponseDto> getAllStudent();
    StudentResponseDto getStudentById(int id);
    StudentResponseDto updateStudentById(int id,StudentRequestDto studentRequestDto);
    void deleteStudent(int id);
}
