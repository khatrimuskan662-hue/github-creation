package com.example.creation.controller;

import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.StudentResponseDto;
import com.example.creation.repository.StudentRepository;
import com.example.creation.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto){
        StudentResponseDto studentResponseDto=studentService.createStudent(studentRequestDto);
        ApiResponse<StudentResponseDto> response=
                new ApiResponse<>(
                        true,
                        "student created sucessfully",
                        studentResponseDto
                );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Page<StudentResponseDto>>> getAllStudent(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

             @RequestParam(defaultValue = "id")
                    String sortBy
    ){
        Page<StudentResponseDto> studentResponseDtoList=
                studentService.getAllStudent(page, size,sortBy);

        ApiResponse<Page<StudentResponseDto>> response=
                new ApiResponse<>(
                        true,
                        "student fetched sucessfully",
                        studentResponseDtoList
                );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<StudentResponseDto>>
    getStudentById(
            @PathVariable int id
    ) {

        StudentResponseDto student =
                studentService.getStudentById(id);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Student fetched successfully",
                        student
                );

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<StudentResponseDto>>
    updateStudent(
            @PathVariable int id,
            @Valid @RequestBody StudentRequestDto dto
    ) {

        StudentResponseDto student =
                studentService.updateStudentById(id,dto);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Student updated successfully",
                        student
                );

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Object>>
    deleteStudent(
            @PathVariable int id
    ) {

        studentService.deleteStudent(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        true,
                        "Student deleted successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }

}
