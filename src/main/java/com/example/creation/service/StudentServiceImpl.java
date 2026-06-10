package com.example.creation.service;

import com.example.creation.dto.request.StudentRequestDto;
import com.example.creation.dto.response.StudentResponseDto;
import com.example.creation.entity.*;
import com.example.creation.exception.ResourceAlreadyExistsException;
import com.example.creation.exception.ResourceNotFoundException;
import com.example.creation.mapper.StudentMapper;
import com.example.creation.mapper.UserMapper;
import com.example.creation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
    private final SubjectRepository subjectRepository;
    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {

       List<SubjectEntity> subject=subjectRepository.findAllById(studentRequestDto.subjectIds());
       if(subject.size() !=studentRequestDto.subjectIds().size()){
           throw new ResourceNotFoundException(
                   "some subject not foound"
           );
       }
        FacultyEntity faculty=facultyRepository.findById(studentRequestDto.facultyId()).orElseThrow(() -> new ResourceAlreadyExistsException(
                "faculty not found"
        ));
        SemesterEntity semester=semesterRepository.findById(studentRequestDto.semesterId()).orElseThrow(() -> new ResourceAlreadyExistsException(
                "semester not found"
        ));
        Role role=roleRepository.findByRoleName("STUDENT").orElseThrow(()-> new ResourceAlreadyExistsException(
                "role not found"
        ));
        UserEntity user=userMapper.toEntity(studentRequestDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user.setRole(role);

        UserEntity saveUser=userRepository.save(user);
        StudentEntity studentEntity=new StudentEntity();
        //studentEntity.setRollNumber(studentRequestDto.rollNumber());
        studentEntity.setUser(saveUser);
        studentEntity.setSubjects(subject);
        studentEntity.setSemester(semester);
        studentEntity.setFaculty(faculty);


        StudentEntity savestudent=studentRepository.save(studentEntity);
        return studentMapper.toResponseDto(savestudent);

    }

    @Override
    public Page<StudentResponseDto> getAllStudent(
            int page,int size,String sortBy) {
        return studentRepository.findAll(PageRequest.of(
                page, size,Sort.by(sortBy)))
                .map(studentMapper::toResponseDto);
    }

    @Override
    public StudentResponseDto getStudentById(int id) {
        StudentEntity studentEntity=studentRepository.findById(id).orElseThrow(
                () -> new ResourceAlreadyExistsException(
                        "student not found"
                )
        );
        return studentMapper.toResponseDto(studentEntity);
    }

    @Override
    public StudentResponseDto updateStudentById(int id, StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity=studentRepository.findById(id)
                .orElseThrow(()-> new ResourceAlreadyExistsException(
                        "student not foound"
                ));
        FacultyEntity facultyEntity=facultyRepository.findById(studentRequestDto.facultyId()).orElseThrow(
                ()-> new ResourceAlreadyExistsException("faculty not found")
        );
        SemesterEntity semesterEntity=semesterRepository.findById(studentRequestDto.semesterId())
                .orElseThrow(()-> new ResourceAlreadyExistsException(
                        "semester not found"
                ));
        //SubjectEntity subject=subjectRepository.findById(studentRequestDto.).orElseThrow(()->
          //      new ResoursenotFoundException(
            //            "subject not found"
              //  ));
        UserEntity userEntity=studentEntity.getUser();
        userEntity.setName(userEntity.getName());
        userEntity.setEmail(userEntity.getEmail());

                    //bCryptPasswordEncoder.encode(userEntity.setPassword(userEntity.getPassword());

        userRepository.save(userEntity);

       // studentEntity.setRollNumber(studentRequestDto.rollNumber());
        studentEntity.setUser(userEntity);
        studentEntity.setFaculty(facultyEntity);
        studentEntity.setSemester(semesterEntity);
       // studentEntity.setSubjects(subject);


        StudentEntity updateStudent=studentRepository.save(studentEntity);
        return studentMapper.toResponseDto(updateStudent);
    }

    @Override
    public void deleteStudent(int id) {

        StudentEntity studentEntity=studentRepository.findById(id)
                .orElseThrow(()-> new ResourceAlreadyExistsException(
                        "student not found"
                ));
        UserEntity user=studentEntity.getUser();
        studentRepository.delete(studentEntity);
        userRepository.delete(user);

    }
}
