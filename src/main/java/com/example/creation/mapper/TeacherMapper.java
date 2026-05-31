package com.example.creation.mapper;

import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.TeacherResponseDto;
import com.example.creation.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(source = "user.name", target = "name")

    @Mapping(source = "user.email", target = "email")

    @Mapping(source = "faculty.id", target = "facultyId")

    @Mapping(
            source = "faculty.facultyName",
            target = "facultyName"
    )

    @Mapping(
            target = "subjects",
            expression =
                    "java(entity.getSubjects()" +
                            ".stream()" +
                            ".map(subject -> subject.getSubjectName())" +
                            ".toList())"
    )

    TeacherResponseDto toResponseDto(
            TeacherEntity entity
    );
}