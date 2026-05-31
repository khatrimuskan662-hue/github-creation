package com.example.creation.service;

import com.example.creation.dto.request.SemesterrequestDto;
import com.example.creation.dto.response.SemesterResponseDto;
import com.example.creation.entity.FacultyEntity;
import com.example.creation.entity.SemesterEntity;
import com.example.creation.exception.ResourseAlreadyExistException;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.SemesterMapper;
import com.example.creation.repository.FacultyRepository;
import com.example.creation.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService{

    private final SemesterRepository semesterRepository;
    private final FacultyRepository facultyRepository;
    private final SemesterMapper semesterMapper;

    @Override
    public SemesterResponseDto createSemester(SemesterrequestDto semesterrequestDto) {
        FacultyEntity faculty=facultyRepository.findById(semesterrequestDto.facultyId()).orElseThrow(() ->
                new ResoursenotFoundException("faculty not found"));
        boolean exists =
                semesterRepository.
                        existsBySemesterNameAndFaculty_id(
                                semesterrequestDto.semesterName(), semesterrequestDto.facultyId()
                        );
        if (exists){
            throw new ResourseAlreadyExistException(
                    "Semester already exist "
            );
        }
        SemesterEntity semester=new SemesterEntity();
        semester.setSemesterName(semesterrequestDto.semesterName());
        semester.setFaculty(faculty);
        SemesterEntity saveSemester=semesterRepository.save(semester);

        return semesterMapper.toResponseDto(saveSemester);

    }

    @Override
    public List<SemesterResponseDto>
    getAllSemesters() {

        return semesterRepository.findAll()
                .stream()
                .map(semesterMapper::toResponseDto)
                .toList();
    }

    @Override
    public SemesterResponseDto getSemesterById(
            int id
    ) {

        SemesterEntity semester =
                semesterRepository.findById(id)
                        .orElseThrow(() ->
                                new ResoursenotFoundException(
                                        "Semester not found"
                                ));

        return semesterMapper
                .toResponseDto(semester);
    }

    @Override
    public SemesterResponseDto updateSemester(
            int id,
            SemesterrequestDto dto
    ) {

        SemesterEntity semester =
                semesterRepository.findById(id)
                        .orElseThrow(() ->
                                new ResoursenotFoundException(
                                        "Semester not found"
                                ));

        FacultyEntity faculty =
                facultyRepository.findById(
                        dto.facultyId()
                ).orElseThrow(() ->
                        new ResoursenotFoundException(
                                "Faculty not found"
                        ));

        semester.setSemesterName(
                dto.semesterName()
        );

        semester.setFaculty(faculty);

        SemesterEntity updatedSemester =
                semesterRepository.save(semester);

        return semesterMapper
                .toResponseDto(updatedSemester);
    }

    @Override
    public void deleteSemester(
            int id
    ) {

        SemesterEntity semester =
                semesterRepository.findById(id)
                        .orElseThrow(() ->
                                new ResoursenotFoundException(
                                        "Semester not found"
                                ));

        semesterRepository.delete(semester);
    }
}
