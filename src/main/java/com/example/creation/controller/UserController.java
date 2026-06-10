package com.example.creation.controller;

import com.example.creation.dto.request.AuthREquestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.RegesterResponseDto;
import com.example.creation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<RegesterResponseDto>>> getAllUsers(){
      List<RegesterResponseDto> users=userService.getAllUser();

      ApiResponse<List<RegesterResponseDto>> response=
              new ApiResponse<>(
                      true,
                      "fetched all users",
                      users
              );
      return ResponseEntity.ok(response);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<ApiResponse<Object>> deleteById(@PathVariable Long id){
    userService.deleteById(id);
    ApiResponse<Object> response=
            new ApiResponse<>(
                    true,
                    "user deleted",
                    null
            );

        return ResponseEntity.ok(response);
    }
}
