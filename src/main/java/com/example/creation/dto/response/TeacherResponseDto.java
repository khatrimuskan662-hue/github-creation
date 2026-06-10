package com.example.creation.dto.response;

import java.util.List;

public record TeacherResponseDto(

        int id,

        String name,

        String email,

        String specialization,

        int facultyId,

        String facultyName,

        List<Integer> subjectIds,

        List<String> subjectNames
) {
}
