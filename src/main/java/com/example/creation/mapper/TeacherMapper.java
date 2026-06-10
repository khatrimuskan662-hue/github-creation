package com.example.creation.mapper;

import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.TeacherResponseDto;
import com.example.creation.entity.SubjectEntity;
import com.example.creation.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.email", target = "email")

    @Mapping(source = "faculty.id", target = "facultyId")
    @Mapping(source = "faculty.facultyName", target = "facultyName")

    @Mapping(target = "subjectIds",
            expression = "java(getSubjectIds(entity.getSubjects()))")

    @Mapping(target = "subjectNames",
            expression = "java(getSubjectNames(entity.getSubjects()))")

    TeacherResponseDto toResponseDto(
            TeacherEntity entity
    );

    default List<Integer> getSubjectIds(
            List<SubjectEntity> subjects
    ) {
        return subjects.stream()
                .map(SubjectEntity::getId)
                .toList();
    }

    default List<String> getSubjectNames(
            List<SubjectEntity> subjects
    ) {
        return subjects.stream()
                .map(SubjectEntity::getSubjectName)
                .toList();
    }
}