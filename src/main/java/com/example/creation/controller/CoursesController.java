package com.example.creation.controller;

import com.example.creation.dto.request.CourseRequestDto;
import com.example.creation.dto.response.CoursesResponseDto;
import com.example.creation.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CoursesController {
    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CoursesResponseDto coursesResponseDto(@Valid @RequestBody CourseRequestDto courseRequestDto){
        return courseService.createCourse(courseRequestDto);
    }

    @GetMapping
    public List<CoursesResponseDto> getallCourse(){
        return courseService.getAllCourses();
    }
}
