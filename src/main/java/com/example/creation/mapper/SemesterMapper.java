package com.example.creation.mapper;


import com.example.creation.dto.response.SemesterResponseDto;
import com.example.creation.entity.SemesterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SemesterMapper {
    @Mapping(source = "faculty.id", target = "facultyId")
    @Mapping(source = "faculty.facultyName", target = "facultyName")
    SemesterResponseDto toResponseDto(
            SemesterEntity entity
    );
}
