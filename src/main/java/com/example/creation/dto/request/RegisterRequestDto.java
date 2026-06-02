package com.example.creation.dto.request;

public record RegisterRequestDto(
        String name,
        String email,
        String password,
        String role
) {
}
