package com.example.creation.service;

import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.dto.response.StudentResponseDto;
import org.springframework.data.domain.Page;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);
    Page<StudentResponseDto> getAllStudent(int page, int size,String sortBy);
    StudentResponseDto getStudentById(int id);
    StudentResponseDto updateStudentById(int id,StudentRequestDto studentRequestDto);
    void deleteStudent(int id);

}
