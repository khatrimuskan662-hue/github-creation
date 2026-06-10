package com.example.creation.mapper;


import com.example.creation.dto.response.AttendenceResponseDto;
import com.example.creation.entity.Attendence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttendenceMapper {
    @Mapping(source = "teacher.user.name", target = "teacherName")
    @Mapping(source = "semester.semesterName", target = "semesterName")
    @Mapping(source = "subject.subjectName", target = "subjectName")
    AttendenceResponseDto toResponseDto(
            Attendence attendance
    );
}
