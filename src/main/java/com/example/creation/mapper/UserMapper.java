package com.example.creation.mapper;


import com.example.creation.dto.request.AuthREquestDto;
import com.example.creation.dto.request.RegisterRequestDto;
import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.*;
import com.example.creation.entity.Role;
import com.example.creation.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
        // @Mapping(target = "name", ignore = true)
        //@Mapping(target = "email", ignore = true)
        //@Mapping(target = "password", ignore = true)
        //@Mapping(source = "role.roleName", target = "role")
    UserEntity toEntity(RegisterRequestDto dto);
    @Mapping(source = "role.roleName", target = "role")
    RegesterResponseDto toResponseDto(UserEntity entity);

    UserEntity toEntity(StudentRequestDto dto);

    UserEntity toEntity(TeacherRequestDto dto);



}
