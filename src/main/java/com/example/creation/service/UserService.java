package com.example.creation.service;


import com.example.creation.dto.request.AuthREquestDto;
import com.example.creation.dto.response.RegesterResponseDto;

import java.util.List;

public interface UserService {
    List<RegesterResponseDto> getAllUser();
    void deleteById(Long id);
}
