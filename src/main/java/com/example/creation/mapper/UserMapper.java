package com.example.creation.mapper;


import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity toEntity(StudentRequestDto dto);
}
