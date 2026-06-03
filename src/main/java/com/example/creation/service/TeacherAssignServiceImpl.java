package com.example.creation.service;

import com.example.creation.dto.request.TeacherAssignRequestDto;
import com.example.creation.dto.response.TeacherAssignResponseDto;
import com.example.creation.entity.*;
import com.example.creation.exception.ResourseAlreadyExistException;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.TeacherAssignMapper;
import com.example.creation.repository.SemesterRepository;
import com.example.creation.repository.SubjectRepository;
import com.example.creation.repository.TeacherAssignmentRepository;
import com.example.creation.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherAssignServiceImpl implements TeacherAssignService{

    private final TeacherRepository teacherRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherAssignmentRepository teacherAssignmentRepository;
    private final TeacherAssignMapper teacherAssignMapper;
    @Override
    public TeacherAssignResponseDto assignTeacher(TeacherAssignRequestDto requestDto) {
        TeacherEntity teacher=teacherRepository.findById(requestDto.teacherId()).orElseThrow(()->
                new ResoursenotFoundException(
                        "Teacher not found"
                ));
        SemesterEntity semester=semesterRepository.findById(requestDto.semesterId()).orElseThrow(()->
                new ResoursenotFoundException(
                        "Semester not found"
                ));
        SubjectEntity subject=subjectRepository.findById(requestDto.subjectId()).orElseThrow(()->
                new ResoursenotFoundException(
                        "Subject not found"
                ));
        TeacherassignmentEntitys teacherassignment=new TeacherassignmentEntitys();
        teacherassignment.setTeacher(teacher);
        teacherassignment.setSemester(semester);
        teacherassignment.setSubject(subject);
        if(teacherAssignmentRepository.existsByTeacherAndSubjectAndSemester(
                teacher,subject,semester
        )){
            throw new ResourseAlreadyExistException(
                    "Teacher already assigned"
            );
        }
        TeacherassignmentEntitys teacherassign=
                teacherAssignmentRepository.save(teacherassignment);
        return teacherAssignMapper.toResponseDto(teacherassign);
    }

    @Override
    public List<TeacherAssignResponseDto> getAllAssignments() {
        return teacherAssignmentRepository.findAll()
                .stream().map(teacherAssignMapper::toResponseDto).toList();
    }

    @Override
    public TeacherAssignResponseDto getAssignmentById(int id) {
        TeacherassignmentEntitys getAssign=
        teacherAssignmentRepository.findById(id).orElseThrow(()->
                new ResoursenotFoundException("assignment not found"));
        return teacherAssignMapper.toResponseDto(getAssign);
    }

    @Override
    public void deleteAssignment(int id) {
        TeacherassignmentEntitys deleteAssign=teacherAssignmentRepository
                .findById(id).orElseThrow(()->
                        new ResoursenotFoundException("Assignment not found"));
        teacherAssignmentRepository.delete(deleteAssign);

    }
}
