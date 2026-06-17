package com.example.creation.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record StudentResponseDto(

        int id,

        String name,

        String email,

        int facultyId,

        String facultyName,

        int semesterId,

        String semesterName,

        List<String> subjects,
        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String photoUrl

) {
}
