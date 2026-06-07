package com.example.creation.dto.response;

public record StudentResponseDto(

        int id,
        int userId,

        String name,

        String email,

        //String rollNumber,

        int facultyId,

        String facultyName,

        int semesterId,

        String semesterName

       // String subjectName
) {
}
