package com.example.creation.service;

import com.example.creation.dto.request.TeacherAssignRequestDto;
import com.example.creation.dto.response.TeacherAssignResponseDto;
import com.example.creation.entity.*;
import com.example.creation.exception.ResourseAlreadyExistException;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.TeacherAssignMapper;
import com.example.creation.repository.*;
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
    private final FacultyRepository facultyRepository;
    @Override
    public TeacherAssignResponseDto assignTeacher(TeacherAssignRequestDto requestDto) {
        TeacherEntity teacher=teacherRepository.findById(requestDto.teacherId()).orElseThrow(()->
                new ResoursenotFoundException(
                        "Teacher not found"
                ));
        FacultyEntity faculty=facultyRepository.findById(requestDto.facultyId()).orElseThrow(()->
                new ResoursenotFoundException(
                        "faculty not found"
                ));
        SemesterEntity semester=semesterRepository.findById(requestDto.semesterId()).orElseThrow(()->
                new ResoursenotFoundException(
                        "Semester not found"
                ));
        SubjectEntity subject=subjectRepository.findById(requestDto.subjectId()).orElseThrow(()->
                new ResoursenotFoundException(
                        "Subject not found"
                ));
        TeacherassignmentEntity teacherassignment=new TeacherassignmentEntity();
        teacherassignment.setTeacher(teacher);
        teacherassignment.setFaculty(faculty);
        teacherassignment.setSemester(semester);
        teacherassignment.setSubject(subject);
        if(teacherAssignmentRepository.existsByTeacherAndFacultyAndSubjectAndSemester(
                teacher,subject,semester,faculty
        )){
            throw new ResourseAlreadyExistException(
                    "Teacher already assigned"
            );
        }
        TeacherassignmentEntity teacherassign=
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
        TeacherassignmentEntity getAssign=
        teacherAssignmentRepository.findById(id).orElseThrow(()->
                new ResoursenotFoundException("assignment not found"));
        return teacherAssignMapper.toResponseDto(getAssign);
    }

    @Override
    public void deleteAssignment(int id) {
        TeacherassignmentEntity deleteAssign=teacherAssignmentRepository
                .findById(id).orElseThrow(()->
                        new ResoursenotFoundException("Assignment not found"));
        teacherAssignmentRepository.delete(deleteAssign);

    }
}
