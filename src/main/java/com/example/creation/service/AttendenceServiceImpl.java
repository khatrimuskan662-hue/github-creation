package com.example.creation.service;

import com.example.creation.dto.request.AtendenceDetailRequestDto;
import com.example.creation.dto.request.AttendenceRequestDto;
import com.example.creation.dto.response.AttendenceDetailResponseDto;
import com.example.creation.dto.response.AttendencePercenteageDto;
import com.example.creation.dto.response.AttendenceResponseDto;
import com.example.creation.entity.*;
import com.example.creation.exception.ResourceNotFoundException;
import com.example.creation.mapper.AttendenceMapper;
import com.example.creation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendenceServiceImpl implements AttendenceService{

    private final AttendenceRepository attendenceRepository;
    private final AtendenceDetailRepository atendenceDetailRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final SemesterRepository semesterRepository;
    private final AttendenceMapper attendenceMapper;

    @Override
    public AttendenceResponseDto createAttendance(AttendenceRequestDto dto) {
        SemesterEntity semester=semesterRepository.findById(dto.semesterId()).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "semester not found"
                ));
        TeacherEntity teacher=teacherRepository.findById(dto.teacherId()).orElseThrow(
                ()->new ResourceNotFoundException(
                        "teacher not found"
                )
        );

        SubjectEntity subject =
                subjectRepository.findById(dto.subjectId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subject not found"
                                ));

        Attendence attendance =
                new Attendence();

        attendance.setAttendanceDate(dto.attendenceDate());
        attendance.setTeacher(teacher);

        attendance.setSemester(semester);

        attendance.setSubject(subject);
Attendence saveAttendence=attendenceRepository.save(attendance);
for (AtendenceDetailRequestDto requestDto:dto.students()){
    StudentEntity student=studentRepository.findById(requestDto.studentId())
            .orElseThrow(()->new ResourceNotFoundException(
                    "student nont found"
            ));
    AttendanceDetailEntity detail=new AttendanceDetailEntity();
    detail.setAttendance(saveAttendence);
    detail.setStudent(student);
    detail.setStatus(requestDto.status());
    atendenceDetailRepository.save(detail);
}

       return attendenceMapper.toResponseDto(saveAttendence);
    }

    @Override
    public List<AttendenceResponseDto> getAllAttendance() {

        return attendenceRepository.findAll()
                .stream()
                .map(attendenceMapper::toResponseDto)
                .toList();
    }

    @Override
    public AttendenceResponseDto getAttendanceById(Integer id) {
        Attendence attendance =
                attendenceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attendance not found"
                                ));

        return attendenceMapper.toResponseDto(attendance);
    }

    @Override
    public AttendencePercenteageDto getAttendancePercentage(int studentId) {

        StudentEntity student=studentRepository.findById(studentId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "student not found"
                ));
        long total=atendenceDetailRepository.countByStudent(student);
        long present=atendenceDetailRepository.countByStudentAndStatus(
                student,
                AttendenceStatus.PRESENT
        );
        double percentage=0;
        if(total>0){
            percentage =
                    ((double) present / total) * 100;
        }
        return new AttendencePercenteageDto(
                student.getId(),
                student.getUser().getName(),
                total,
                present,
                percentage
        );
    }
}



