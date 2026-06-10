package com.example.creation.controller;

import com.example.creation.dto.request.SubjectRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.SubjectResponseDto;
import com.example.creation.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SubjectResponseDto>> createSubject(
            @Valid @RequestBody SubjectRequestDto subjectRequestDto
            ){
        SubjectResponseDto subjectResponseDto=subjectService.createSubject(subjectRequestDto);
        ApiResponse<SubjectResponseDto> response=
                new ApiResponse<>(
                        true,
                        "subject Created sucessfully",
                        subjectResponseDto
                );
        return ResponseEntity.ok(response);
    }

@GetMapping
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<ApiResponse<List<SubjectResponseDto>>>
getAllSubject() {

    List<SubjectResponseDto> subjects =
            subjectService.getAllSubjects();

    ApiResponse<List<SubjectResponseDto>> response =
            new ApiResponse<>(
                    true,
                    "Subjects fetched successfully",
                    subjects
            );

    return ResponseEntity.ok(response);
}

@GetMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<ApiResponse<SubjectResponseDto>>
getSubjectById(
        @PathVariable int id
) {

    SubjectResponseDto subject =
            subjectService.getSubjectById(id);

    ApiResponse<SubjectResponseDto> response =
            new ApiResponse<>(
                    true,
                    "Subject fetched successfully",
                    subject
            );

    return ResponseEntity.ok(response);
}

@PutMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<ApiResponse<SubjectResponseDto>>
updateSubject(
        @PathVariable int id,
        @Valid @RequestBody SubjectRequestDto dto
) {

    SubjectResponseDto subject =
            subjectService.updateSubject(id, dto);

    ApiResponse<SubjectResponseDto> response =
            new ApiResponse<>(
                    true,
                    "Subject updated successfully",
                    subject
            );

    return ResponseEntity.ok(response);
}

@DeleteMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<ApiResponse<Object>>
deleteSubject(
        @PathVariable int id
) {

    subjectService.deleteSubject(id);

    ApiResponse<Object> response =
            new ApiResponse<>(
                    true,
                    "Subject deleted successfully",
                    null
            );

    return ResponseEntity.ok(response);
}
}
