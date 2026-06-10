package com.example.creation.dto.response;

import java.util.List;

public record StudentResponseDto(

        int id,
        //int userId,

        String name,

        String email,

        //String rollNumber,

        int facultyId,

        String facultyName,

        int semesterId,

        String semesterName,

        List<String> subjects

       // String subjectName
) {
}
