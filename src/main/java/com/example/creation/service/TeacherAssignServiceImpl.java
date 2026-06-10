package com.example.creation.service;

import com.example.creation.dto.request.TeacherAssignRequestDto;
import com.example.creation.dto.response.TeacherAssignResponseDto;
import com.example.creation.entity.*;
import com.example.creation.exception.ResourceNotFoundException;
import com.example.creation.exception.ResourceAlreadyExistsException;
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
                new ResourceAlreadyExistsException(
                        "Teacher not found"
                ));
        //FacultyEntity faculty=facultyRepository.findById(requestDto.facultyId()).orElseThrow(()->
          //      new ResourceAlreadyExistsException(
            //            "faculty not found"
              //  ));
        SemesterEntity semester=semesterRepository.findById(requestDto.semesterId()).orElseThrow(()->
                new ResourceAlreadyExistsException(
                        "Semester not found"
                ));
        SubjectEntity subject=subjectRepository.findById(requestDto.subjectId()).orElseThrow(()->
                new ResourceAlreadyExistsException(
                        "Subject not found"
                ));
        TeacherassignmentEntity teacherassignment=new TeacherassignmentEntity();
        teacherassignment.setTeacher(teacher);
       // teacherassignment.setFaculty(faculty);
        teacherassignment.setSemester(semester);
        teacherassignment.setSubject(subject);
        if(teacherAssignmentRepository.existsByTeacherAndSubjectAndSemester(
                teacher,subject,semester
        )){
            throw new ResourceNotFoundException(
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
                new ResourceAlreadyExistsException("assignment not found"));
        return teacherAssignMapper.toResponseDto(getAssign);
    }

    @Override
    public void deleteAssignment(int id) {
        TeacherassignmentEntity deleteAssign=teacherAssignmentRepository
                .findById(id).orElseThrow(()->
                        new ResourceAlreadyExistsException("Assignment not found"));
        teacherAssignmentRepository.delete(deleteAssign);

    }
}
