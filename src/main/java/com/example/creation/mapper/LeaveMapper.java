package com.example.creation.mapper;

import com.example.creation.dto.response.LeaveResponseDto;
import com.example.creation.entity.LeaveEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveMapper {
    @Mapping(source = "teacher.user.name", target = "teacherName")
    LeaveResponseDto toResponseDto(
            LeaveEntity entity
    );
}
