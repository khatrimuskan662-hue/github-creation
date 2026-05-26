package com.example.creation.mapper;


import com.example.creation.dto.response.CoursesResponseDto;
import com.example.creation.entity.Courses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoursesMapper {
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.name", target = "teacherName")
    CoursesResponseDto toResponseDto(Courses entity);
}
