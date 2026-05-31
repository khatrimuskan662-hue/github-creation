package com.example.creation.mapper;

import com.example.creation.dto.response.SubjectResponseDto;
import com.example.creation.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(source = "semester.id", target = "semesterId")
    @Mapping(source = "semester.semesterName", target = "semesterName")
    SubjectResponseDto toResponseDto(
            SubjectEntity entity
    );
}

