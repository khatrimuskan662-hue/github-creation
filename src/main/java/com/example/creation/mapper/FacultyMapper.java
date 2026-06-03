package com.example.creation.mapper;

import com.example.creation.dto.request.FacultyRequestDto;
import com.example.creation.dto.response.FacultyResponseDto;
import com.example.creation.entity.FacultyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
    @Mapping(target = "id", ignore = true)

    @Mapping(target = "semesters", ignore = true)

    @Mapping(target = "teachers", ignore = true)

    @Mapping(target = "students", ignore = true)
    FacultyEntity toEntity(FacultyRequestDto facultyRequestDto);
    FacultyResponseDto toResponse(FacultyEntity facultyEntity);
}
