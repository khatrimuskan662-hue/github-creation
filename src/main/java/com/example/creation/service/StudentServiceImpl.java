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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.security.Key;
import java.util.List;
import java.util.Optional;

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
    private final FileUploadService fileUploadService;
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
    @Cacheable("students")
    public Page<StudentResponseDto> getAllStudent(
            int page,int size,String sortBy,String direction) {
        Sort sort=direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).descending():
                Sort.by(sortBy).ascending();
        return studentRepository.findAll(PageRequest.of(
                page, size,sort))
                .map(studentMapper::toResponseDto);
    }

    @Override
    @Cacheable(
            value = "students",
            key = "#id"
    )
    public StudentResponseDto getStudentById(int id) {

        System.out.println("DATABASE HIT");

        StudentEntity studentEntity=studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "student not found"
                )
        );
        return studentMapper.toResponseDto(studentEntity);
    }

    @Override
    @CachePut(value = "students",key = "#id")
    public StudentResponseDto updateStudentById(int id, StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity=studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "student not found"
                ));
        FacultyEntity facultyEntity=facultyRepository.findById(studentRequestDto.facultyId()).orElseThrow(
                ()-> new ResourceNotFoundException("faculty not found")
        );
        SemesterEntity semesterEntity=semesterRepository.findById(studentRequestDto.semesterId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "semester not found"
                ));
        List<SubjectEntity> subject=subjectRepository.findAllById(studentRequestDto.subjectIds());
        if(subject.size() !=studentRequestDto.subjectIds().size()){
            throw new ResourceNotFoundException(
                    "some subject not foound"
            );
        }
        UserEntity userEntity=studentEntity.getUser();
        userEntity.setName(userEntity.getName());
        userEntity.setEmail(userEntity.getEmail());

                    //bCryptPasswordEncoder.encode(userEntity.setPassword(userEntity.getPassword());

        userRepository.save(userEntity);

       // studentEntity.setRollNumber(studentRequestDto.rollNumber());
        studentEntity.setUser(userEntity);
        studentEntity.setFaculty(facultyEntity);
        studentEntity.setSemester(semesterEntity);
        studentEntity.setSubjects(subject);


        StudentEntity updateStudent=studentRepository.save(studentEntity);
        return studentMapper.toResponseDto(updateStudent);
    }

    @Override
    @CacheEvict(value = "students",key = "#id")
    public void deleteStudent(int id) {

        StudentEntity student=studentRepository.findById(id)
                .orElseThrow(()-> new ResourceAlreadyExistsException(
                        "student not found"
                ));
       // student.setDeleted(true);
       // student.getUser().setDeleted(true);
       // studentRepository.save(student);
       // userRepository.save(student.getUser());


        UserEntity user=student.getUser();
        studentRepository.delete(student);
        userRepository.delete(user);

    }

   @Override
    public List<StudentResponseDto> searchStudent(String keyword) {
        return studentRepository.findByUser_NameContainingIgnoreCase(
               keyword
        ).stream().map(studentMapper::toResponseDto).toList();
   }

    @Override
    public StudentResponseDto uploadPhoto(int studentId, MultipartFile file) {

        StudentEntity student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"
                                ));

        try {

            String fileName =
                    fileUploadService.uploadFile(file);

            student.setPhotoPath(fileName);

            StudentEntity saved =
                    studentRepository.save(student);

            return studentMapper.toResponseDto(saved);

        } catch (Exception e) {

            throw new RuntimeException(
                    "File upload failed"
            );
        }


    }}
