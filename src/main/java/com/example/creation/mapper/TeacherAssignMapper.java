package com.example.creation.mapper;

import com.example.creation.dto.response.TeacherAssignResponseDto;
import com.example.creation.entity.TeacherassignmentEntitys;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherAssignMapper {
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(
            expression = "java(entity.getTeacher().getUser().getName())",
            target = "teacherName"
    )

    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "subject.subjectName", target = "subjectName")

    @Mapping(source = "semester.id", target = "semesterId")
    @Mapping(source = "semester.semesterName", target = "semesterName")
    TeacherAssignResponseDto toResponseDto(TeacherassignmentEntitys entity);
}


