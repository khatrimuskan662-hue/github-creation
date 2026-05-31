package com.example.creation.service;

import com.example.creation.dto.request.SubjectRequestDto;
import com.example.creation.dto.response.SubjectResponseDto;
import com.example.creation.entity.SemesterEntity;
import com.example.creation.entity.SubjectEntity;
import com.example.creation.exception.ResourseAlreadyExistException;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.SubjectMapper;
import com.example.creation.repository.SemesterRepository;
import com.example.creation.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectResponseDto createSubject(SubjectRequestDto subjectRequestDto) {
        SemesterEntity semester=semesterRepository.findById(subjectRequestDto.semesterId()).orElseThrow(() ->
                new ResoursenotFoundException("semester not found"));

        boolean exists=subjectRepository.existsBySubjectNameAndSemester_id(subjectRequestDto.subjectName(),subjectRequestDto.semesterId());

        if (exists){
            throw new ResourseAlreadyExistException("subject already exist");



        }
        SubjectEntity subject=new SubjectEntity();
        subject.setSubjectName(subjectRequestDto.subjectName());
        subject.setSemester(semester);
        SubjectEntity saveSubject=subjectRepository.save(subject);

        return subjectMapper.toResponseDto(saveSubject);
    }

    @Override
    public List<SubjectResponseDto>
    getAllSubjects() {

        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toResponseDto)
                .toList();
    }

    @Override
    public SubjectResponseDto getSubjectById(
            int id
    ) {

        SubjectEntity subject =
                subjectRepository.findById(id)
                        .orElseThrow(() ->
                                new ResoursenotFoundException(
                                        "Subject not found"
                                ));

        return subjectMapper
                .toResponseDto(subject);
    }

    @Override
    public SubjectResponseDto updateSubject(
            int id,
            SubjectRequestDto dto
    ) {

        SubjectEntity subject =
                subjectRepository.findById(id)
                        .orElseThrow(() ->
                                new ResoursenotFoundException(
                                        "Subject not found"
                                ));

        SemesterEntity semester =
                semesterRepository.findById(
                        dto.semesterId()
                ).orElseThrow(() ->
                        new ResoursenotFoundException(
                                "Semester not found"
                        ));

        subject.setSubjectName(
                dto.subjectName()
        );

        subject.setSemester(semester);

        SubjectEntity updatedSubject =
                subjectRepository.save(subject);

        return subjectMapper
                .toResponseDto(updatedSubject);
    }

    @Override
    public void deleteSubject(
            int id
    ) {

        SubjectEntity subject =
                subjectRepository.findById(id)
                        .orElseThrow(() ->
                                new ResoursenotFoundException(
                                        "Subject not found"
                                ));

        subjectRepository.delete(subject);
    }
}
