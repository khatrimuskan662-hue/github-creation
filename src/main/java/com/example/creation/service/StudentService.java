package com.example.creation.service;

import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.dto.response.StudentResponseDto;
import com.example.creation.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);
    Page<StudentResponseDto> getAllStudent(
            int page, int size,String sortBy,String direction);
    StudentResponseDto getStudentById(int id);
    StudentResponseDto updateStudentById(int id,StudentRequestDto studentRequestDto);
    void deleteStudent(int id);
    List<StudentResponseDto>searchStudent(String keyword);
    //StudentResponseDto restoreStudent(int id);
    StudentResponseDto uploadPhoto(
            int studentId,
            MultipartFile file
    );
}
