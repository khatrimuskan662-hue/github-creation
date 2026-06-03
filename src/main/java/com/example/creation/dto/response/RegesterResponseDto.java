package com.example.creation.dto.response;

public record RegesterResponseDto(
        Long id,
        String name,
        String email,
        String role
) {
}
