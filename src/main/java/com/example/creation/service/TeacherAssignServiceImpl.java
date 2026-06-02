package com.example.creation.service;

import com.example.creation.dto.request.TeacherAssignRequestDto;
import com.example.creation.dto.response.TeacherAssignResponseDto;
import com.example.creation.entity.SemesterEntity;
import com.example.creation.entity.SubjectEntity;
import com.example.creation.entity.TeacherEntity;
import com.example.creation.entity.TeacherassignmentEntity;
import com.example.creation.exception.ResourseAlreadyExistException;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.TeacherAssignMapper;
import com.example.creation.repository.SemesterRepository;
import com.example.creation.repository.SubjectRepository;
import com.example.creation.repository.TeacherAssignmentRepository;
import com.example.creation.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        TeacherassignmentEntity teacherassignment=new TeacherassignmentEntity();
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
        TeacherassignmentEntity teacherassign=
                teacherAssignmentRepository.save(teacherassignment);
        return teacherAssignMapper.toResponseDto(teacherassign);
    }
}
