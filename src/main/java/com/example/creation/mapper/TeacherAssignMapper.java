package com.example.creation.mapper;

import com.example.creation.dto.response.TeacherAssignResponseDto;
import com.example.creation.entity.TeacherassignmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherAssignMapper {
    @Mapping(source = "id", target = "id")

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.user.name", target = "teacherName")

    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "subject.subjectName", target = "subjectName")

    @Mapping(source = "semester.id", target = "semesterId")
    @Mapping(source = "semester.semesterName", target = "semesterName")
    TeacherAssignResponseDto toResponseDto(TeacherassignmentEntity entity);
}


