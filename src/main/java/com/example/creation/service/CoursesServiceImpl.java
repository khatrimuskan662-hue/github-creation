package com.example.creation.service;

import com.example.creation.dto.request.CourseRequestDto;
import com.example.creation.dto.response.CoursesResponseDto;
import com.example.creation.entity.Courses;
import com.example.creation.entity.TeacherEntity;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.CoursesMapper;
import com.example.creation.repository.CoursesRepository;
import com.example.creation.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CourseService {

    private final CoursesRepository coursesRepository;
    private final TeacherRepository teacherRepository;
    private final CoursesMapper coursesMapper;

    @Override
    public CoursesResponseDto createCourse(CourseRequestDto dto) {
        TeacherEntity teacher = teacherRepository.findById(dto.teacherId())
                .orElseThrow(() ->
                        new ResoursenotFoundException(
                                "Teacher not found with id " + dto.teacherId()
                        ));

        Courses course = new Courses();
        course.setTitle(dto.title());
        course.setDuration(dto.duration());
        course.setTeacher(teacher);

        Courses savedCourse = coursesRepository.save(course);

        return coursesMapper.toResponseDto(savedCourse);
    }

    @Override
    public List<CoursesResponseDto> getAllCourses() {
        return coursesRepository.findAll()
                .stream()
                .map(coursesMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
