package com.example.creation.mapper;


import com.example.creation.dto.response.StudentResponseDto;
import com.example.creation.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "faculty.id", target = "facultyId")
    @Mapping(source = "faculty.facultyName", target = "facultyName")
    @Mapping(source = "semester.id", target = "semesterId")
    @Mapping(source = "semester.semesterName", target = "semesterName")
   // @Mapping(source = "subject.subjectName", target = "subjectName")
    StudentResponseDto toResponseDto(
            StudentEntity entity
    );
}
