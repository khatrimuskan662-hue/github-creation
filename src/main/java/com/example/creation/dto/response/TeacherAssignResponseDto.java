package com.example.creation.dto.response;

public record TeacherAssignResponseDto(

        Integer id,
        Integer teacherId,
        String teacherName,

        Integer facultyId,
        String facultyName,

        Integer subjectId,
        String subjectName,

        Integer semesterId,
        String semesterName
) {
}
