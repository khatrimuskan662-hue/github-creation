package com.example.creation.service;

import com.example.creation.dto.request.FacultyRequestDto;
import com.example.creation.dto.response.FacultyResponseDto;

import java.util.List;

public interface FacultyService {
    FacultyResponseDto createFaculty(FacultyRequestDto facultyRequestDto);
    List<FacultyResponseDto> getAllFaculties();

    FacultyResponseDto getFacultyById(
            int id
    );

    FacultyResponseDto updateFaculty(
            int id,
            FacultyRequestDto dto
    );

    void deleteFaculty(
            int id
    );
}
