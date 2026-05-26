package com.example.creation.controller;

import com.example.creation.dto.request.AuthREquestDto;
import com.example.creation.dto.request.RegisterRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.AuthResponseDto;
import com.example.creation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequestDto registerRequestDto){
        String message= authService.register(registerRequestDto);
        ApiResponse<String> response =
                new ApiResponse<>(true, message, null);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@RequestBody AuthREquestDto authREquestDto){
        AuthResponseDto authResponse = authService.login(authREquestDto);

        ApiResponse<AuthResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Login successful",
                        authResponse
                );

        return ResponseEntity.ok(response);
    }
}
