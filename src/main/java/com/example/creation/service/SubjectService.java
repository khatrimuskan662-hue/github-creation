package com.example.creation.service;

import com.example.creation.dto.request.SubjectRequestDto;
import com.example.creation.dto.response.SubjectResponseDto;

import java.util.List;

public interface SubjectService {
    SubjectResponseDto createSubject(SubjectRequestDto subjectRequestDto);
    List<SubjectResponseDto> getAllSubjects();

    SubjectResponseDto getSubjectById(
            int id
    );

    SubjectResponseDto updateSubject(
            int id,
            SubjectRequestDto dto
    );

    void deleteSubject(
            int id
    );
}
