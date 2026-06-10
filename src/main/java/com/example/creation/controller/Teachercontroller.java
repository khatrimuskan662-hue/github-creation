package com.example.creation.controller;

import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.ApiResponse;
import com.example.creation.dto.response.TeacherResponseDto;
import com.example.creation.mapper.TeacherMapper;
import com.example.creation.repository.TeacherRepository;
import com.example.creation.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
public class Teachercontroller {

    private final TeacherRepository teacherRepository;

    private final TeacherService teacherService ;

    private final TeacherMapper teacherMapper;

   @PostMapping("/save")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeacherResponseDto>> save(@Valid @RequestBody TeacherRequestDto teacherRequestDto){

        TeacherResponseDto savedTeacher = teacherService.saveTeacher(teacherRequestDto);

        ApiResponse<TeacherResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Teacher created successfully",
                        savedTeacher
                );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

   @GetMapping
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<ApiResponse<List<TeacherResponseDto>>> getallTeacher(){

       List<TeacherResponseDto> teachers = teacherService.teacherList();

       ApiResponse<List<TeacherResponseDto>> response =
               new ApiResponse<>(
                       true,
                       "Teachers fetched successfully",
                       teachers
               );

       return ResponseEntity.ok(response);
    }

   @DeleteMapping("/delete/{id}")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable int id){
       teacherService.deleteTeacher(id);
       ApiResponse<String> response =
               new ApiResponse<>(
                       true,
                       "Teacher deleted successfully",
                       null
               );

       return ResponseEntity.ok(response);
   }

   @PutMapping("/update/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<ApiResponse<TeacherResponseDto>> updateTeacher(@PathVariable int id,@Valid @RequestBody TeacherRequestDto teacherRequestDto) {
       TeacherResponseDto updateTeacher=teacherService.updateTeacher(id,teacherRequestDto);
       ApiResponse<TeacherResponseDto> response =
               new ApiResponse<>(
                       true,
                       "Teacher updated successfully",
                       updateTeacher
               );

       return ResponseEntity.ok(response);


}
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeacherResponseDto>>
    getTeacherById(
            @PathVariable int id
    ) {

        TeacherResponseDto teacher =
                teacherService.getByID(id);

        ApiResponse<TeacherResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Teacher fetched successfully",
                        teacher
                );

        return ResponseEntity.ok(response);
    }}
