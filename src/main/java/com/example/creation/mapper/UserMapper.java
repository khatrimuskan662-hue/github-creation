package com.example.creation.mapper;


import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.dto.response.RegesterResponseDto;
import com.example.creation.entity.Role;
import com.example.creation.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
   @Mapping(target = "id", ignore = true)
   //@Mapping(target = "role", ignore = true)
   // @Mapping(target = "name", ignore = true)
    //@Mapping(target = "email", ignore = true)
    //@Mapping(target = "password", ignore = true)
   //@Mapping(source = "role.roleName", target = "role")
    UserEntity toEntity(StudentRequestDto dto);
    RegesterResponseDto toResponse(UserEntity user);
    default String map(Role role) {
        return role == null ? null : role.getRoleName();
}}
