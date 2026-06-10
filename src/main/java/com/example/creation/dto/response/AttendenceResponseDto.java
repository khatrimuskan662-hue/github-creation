package com.example.creation.dto.response;

import java.time.LocalDate;
import java.util.List;

public record AttendenceResponseDto(
        Integer id,

        LocalDate attendanceDate,

        String teacherName,

        String semesterName,

        String subjectName

        //List<AttendenceDetailResponseDto> students
) {
}
