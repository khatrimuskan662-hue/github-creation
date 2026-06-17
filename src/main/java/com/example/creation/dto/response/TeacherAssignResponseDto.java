package com.example.creation.dto.response;

import java.time.LocalDateTime;

public record TeacherAssignResponseDto(

        Integer id,
        Integer teacherId,
        String teacherName,

        //Integer facultyId,
        //String facultyName,

        Integer subjectId,
        String subjectName,

        Integer semesterId,
        String semesterName
) {
}
