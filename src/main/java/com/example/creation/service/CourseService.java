package com.example.creation.service;

import com.example.creation.dto.request.CourseRequestDto;
import com.example.creation.dto.response.CoursesResponseDto;

import java.util.List;

public interface CourseService {
    CoursesResponseDto createCourse(CourseRequestDto dto);

    List<CoursesResponseDto> getAllCourses();
}
