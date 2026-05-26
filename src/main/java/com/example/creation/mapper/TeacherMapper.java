package com.example.creation.mapper;

import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.TeacherResponseDto;
import com.example.creation.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherEntity toEntity(TeacherRequestDto dto);

    TeacherResponseDto toResponseDto(TeacherEntity entity);

    void updateTeacherToEntity(TeacherRequestDto teacherRequestDto, @MappingTarget TeacherEntity teacherEntity);

}
