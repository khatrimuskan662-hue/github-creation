package com.example.creation.service;

import com.example.creation.dto.request.CalenderRequestDto;
import com.example.creation.dto.request.FacultyRequestDto;
import com.example.creation.dto.response.CalenderResponseDto;
import com.example.creation.dto.response.FacultyResponseDto;

import java.util.List;

public interface CalenderService {
    CalenderResponseDto createEvent(CalenderRequestDto dto);
    List<CalenderResponseDto> getAllEvents();

    CalenderResponseDto getEventById(
            int id
    );

    CalenderResponseDto updateEvent(
            int id,
            CalenderRequestDto dto
    );

    void deleteEvent(
            int id
    );
}
