package com.example.creation.service;

import com.example.creation.dto.request.FacultyRequestDto;
import com.example.creation.dto.response.FacultyResponseDto;
import com.example.creation.entity.FacultyEntity;
import com.example.creation.exception.ResourceNotFoundException;
import com.example.creation.exception.ResourceAlreadyExistsException;
import com.example.creation.mapper.FacultyMapper;
import com.example.creation.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    @Override
    public FacultyResponseDto createFaculty(FacultyRequestDto facultyRequestDto) {
        boolean exists =
                facultyRepository.existsByFacultyName(
                        facultyRequestDto.facultyName()
                );

        if (exists) {
            throw new ResourceNotFoundException(
                    "Faculty already exists"
            );
        }
        if(facultyRepository.existsByFacultyNameIgnoreCase(
                facultyRequestDto.facultyName().trim()
        )){
            throw new ResourceNotFoundException("faculty already exist");
        }

        FacultyEntity faculty =
                facultyMapper.toEntity(facultyRequestDto);
        String facultyName= facultyRequestDto.facultyName()
                .trim().toUpperCase();

        FacultyEntity savedFaculty =
                facultyRepository.save(faculty);

        return facultyMapper.toResponse(savedFaculty);
    }

    @Override
    public List<FacultyResponseDto>
    getAllFaculties() {

        return facultyRepository.findAll()
                .stream()
                .map(facultyMapper::toResponse)
                .toList();
    }

    @Override
    public FacultyResponseDto getFacultyById(
            int id
    ) {

        FacultyEntity faculty =
                facultyRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceAlreadyExistsException(
                                        "Faculty not found"
                                ));

        return facultyMapper
                .toResponse(faculty);
    }

    @Override
    public FacultyResponseDto updateFaculty(
            int id,
            FacultyRequestDto dto
    ) {

        FacultyEntity faculty =
                facultyRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceAlreadyExistsException(
                                        "Faculty not found"
                                ));

        faculty.setFacultyName(
                dto.facultyName()
        );

        FacultyEntity updatedFaculty =
                facultyRepository.save(faculty);

        return facultyMapper
                .toResponse(updatedFaculty);
    }

    @Override
    public void deleteFaculty(
            int id
    ) {

        FacultyEntity faculty =
                facultyRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceAlreadyExistsException(
                                        "Faculty not found"
                                ));

        facultyRepository.delete(faculty);
    }
}