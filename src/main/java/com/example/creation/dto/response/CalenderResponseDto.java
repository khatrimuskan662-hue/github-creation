package com.example.creation.dto.response;

import java.time.LocalDateTime;

public record CalenderResponseDto(
        Integer id,
        String title,
        String description,
        LocalDateTime eventDate
) {
}
