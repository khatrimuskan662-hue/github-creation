package com.example.creation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record AttendenceRequestDto(

        @NotNull
        Integer teacherId,

        @NotNull
        Integer subjectId,

        @NotNull
        Integer semesterId,

        LocalDate attendenceDate,

        List<AtendenceDetailRequestDto> students

) {
}
