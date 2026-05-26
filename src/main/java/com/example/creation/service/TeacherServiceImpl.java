package com.example.creation.service;

import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.TeacherResponseDto;
import com.example.creation.entity.TeacherEntity;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.TeacherMapper;
import com.example.creation.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public TeacherResponseDto saveTeacher(TeacherRequestDto teacherRequestDto) {

       TeacherEntity teacherEntity=teacherMapper.toEntity(teacherRequestDto);
       TeacherEntity saveentity=teacherRepository.save(teacherEntity);

        return teacherMapper.toResponseDto(saveentity);
    }

    @Override
    public List<TeacherResponseDto> teacherList() {

        return teacherRepository.findAll().
                stream().map(teacherMapper::toResponseDto).
                collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String deleteTeacher(int id) {
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ResoursenotFoundException("Teacher not found with id " + id));

        teacherRepository.delete(teacher);

        return "teacher deleted";
    }

    @Override
    public TeacherResponseDto updateTeacher(int id, TeacherRequestDto teacherRequestDto) {

        TeacherEntity teacherEntity1=teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ResoursenotFoundException("Teacher not found with id " + id));
       teacherMapper.updateTeacherToEntity(teacherRequestDto,teacherEntity1 );
       TeacherEntity updateTeacher=teacherRepository.save(teacherEntity1);

            return teacherMapper.toResponseDto(updateTeacher);


    }
}
