package com.example.creation.service;

import com.example.creation.dto.request.SemesterrequestDto;
import com.example.creation.dto.response.SemesterResponseDto;

import java.util.List;

public interface SemesterService {
    SemesterResponseDto createSemester(SemesterrequestDto semesterrequestDto);
    List<SemesterResponseDto> getAllSemesters();

    SemesterResponseDto getSemesterById(
            int id
    );

    SemesterResponseDto updateSemester(
            int id,
            SemesterrequestDto dto
    );

    void deleteSemester(
            int id
    );
}
