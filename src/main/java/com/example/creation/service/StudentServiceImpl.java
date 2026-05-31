package com.example.creation.service;

import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.dto.response.StudentResponseDto;
import com.example.creation.entity.*;
import com.example.creation.exception.ResourseAlreadyExistException;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.StudentMapper;
import com.example.creation.mapper.UserMapper;
import com.example.creation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;
    private final SemesterRepository semesterRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
       boolean rollExists=studentRepository.existsByRollNumber(studentRequestDto.rollNumber());
       if(rollExists){
           throw new ResourseAlreadyExistException("roll number already exist");

       }
       boolean emailExist=userRepository.findByEmail(studentRequestDto.email()).isPresent();
       if(emailExist){
           throw new ResourseAlreadyExistException("email already exist");
       }
        FacultyEntity faculty=facultyRepository.findById(studentRequestDto.facultyId()).orElseThrow(() -> new ResoursenotFoundException(
                "faculty not found"
        ));
        SemesterEntity semester=semesterRepository.findById(studentRequestDto.semesterId()).orElseThrow(() -> new ResoursenotFoundException(
                "semester not found"
        ));
        Role role=roleRepository.findByRoleName("STUDENT").orElseThrow(()-> new ResoursenotFoundException(
                "role not found"
        ));
        UserEntity user=userMapper.toEntity(studentRequestDto);
        user.setPassword(bCryptPasswordEncoder.encode(studentRequestDto.password()));

        user.setRole(role);

        UserEntity saveUser=userRepository.save(user);
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setRollNumber(studentRequestDto.rollNumber());
        studentEntity.setUser(saveUser);
        studentEntity.setFaculty(faculty);
        studentEntity.setSemester(semester);

        StudentEntity savestudent=studentRepository.save(studentEntity);
        return studentMapper.toResponseDto(savestudent);

    }

    @Override
    public List<StudentResponseDto> getAllStudent() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponseDto)
                .toList();
    }

    @Override
    public StudentResponseDto getStudentById(int id) {
        StudentEntity studentEntity=studentRepository.findById(id).orElseThrow(
                () -> new ResoursenotFoundException(
                        "student not found"
                )
        );
        return studentMapper.toResponseDto(studentEntity);
    }

    @Override
    public StudentResponseDto updateStudentById(int id, StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity=studentRepository.findById(id)
                .orElseThrow(()-> new ResoursenotFoundException(
                        "student not foound"
                ));
        FacultyEntity facultyEntity=facultyRepository.findById(studentRequestDto.facultyId()).orElseThrow(
                ()-> new ResoursenotFoundException("faculty not found")
        );
        SemesterEntity semesterEntity=semesterRepository.findById(studentRequestDto.semesterId())
                .orElseThrow(()-> new ResoursenotFoundException(
                        "semester not found"
                ));
        UserEntity userEntity=studentEntity.getUser();
        userEntity.setName(studentRequestDto.name());
        userEntity.setEmail(studentRequestDto.email());
        if(studentRequestDto.password()!=null&& !studentRequestDto.password().isBlank()){
            userEntity.setPassword(
                    bCryptPasswordEncoder.encode(studentRequestDto.password())
            );
        }
        userRepository.save(userEntity);

        studentEntity.setRollNumber(studentRequestDto.rollNumber());
        studentEntity.setFaculty(facultyEntity);
        studentEntity.setSemester(semesterEntity);

        StudentEntity updateStudent=studentRepository.save(studentEntity);
        return studentMapper.toResponseDto(updateStudent);
    }

    @Override
    public void deleteStudent(int id) {

        StudentEntity studentEntity=studentRepository.findById(id)
                .orElseThrow(()-> new ResoursenotFoundException(
                        "student not found"
                ));
        UserEntity user=studentEntity.getUser();
        studentRepository.delete(studentEntity);
        userRepository.delete(user);

    }
}
