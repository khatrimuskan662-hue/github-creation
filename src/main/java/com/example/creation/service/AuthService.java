package com.example.creation.service;

import com.example.creation.dto.request.AuthREquestDto;
import com.example.creation.dto.request.RegisterRequestDto;
import com.example.creation.dto.response.AuthResponseDto;

public interface AuthService {
    String register(RegisterRequestDto dto);
    AuthResponseDto login(AuthREquestDto authREquestDto);
}
