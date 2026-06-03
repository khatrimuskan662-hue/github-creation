package com.example.creation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequestDto(

        @NotBlank
        String name,

        @Email
        String email,

        @NotBlank
        String password,

        @NotNull
        Integer facultyId,

        @NotNull
        Integer semesterId
) {
}
