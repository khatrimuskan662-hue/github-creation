package com.example.creation.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.creation.dto.request.CalenderRequestDto;
import com.example.creation.dto.response.CalenderResponseDto;
import com.example.creation.entity.CalenderEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CalenderMapper {
    @Mapping(target = "id", ignore = true)
    CalenderEvent toEntity(CalenderRequestDto dto);
    CalenderResponseDto toResponse(CalenderEvent event);
}
