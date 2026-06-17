package com.example.creation.dto.response;

import java.time.LocalDateTime;

public record RegesterResponseDto(
        Long id,
        String name,
        String email,
        String role
) {
}
