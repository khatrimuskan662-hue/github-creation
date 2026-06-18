package com.example.creation.service;

import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.TeacherResponseDto;
import com.example.creation.entity.*;
import com.example.creation.exception.ResourceAlreadyExistsException;
import com.example.creation.exception.ResourceNotFoundException;
import com.example.creation.mapper.TeacherMapper;
import com.example.creation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FacultyRepository facultyRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherMapper teacherMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public TeacherResponseDto saveTeacher(TeacherRequestDto teacherRequestDto) {

            if(userRepository.existsByEmail(teacherRequestDto.email())){
                throw new ResourceAlreadyExistsException(
                        "email already exist"
                );
            }
            FacultyEntity faculty =
                    facultyRepository.findById(
                            teacherRequestDto.facultyId()
                    ).orElseThrow(() ->
                            new ResourceAlreadyExistsException(
                                    "Faculty not found"
                          ));
            List<SubjectEntity> subjects =
                    subjectRepository.findAllById(
                            teacherRequestDto.subjectIds()
                    );

            if (subjects.isEmpty()) {
                throw new ResourceNotFoundException(
                        "Subjects not found"
                );
            }
            if(subjects.size()!=teacherRequestDto.subjectIds().size()){
                throw new ResourceNotFoundException(
                        "some subject not found"
                );
            }

            Role role = roleRepository.findByRoleName("TEACHER")
                    .orElseThrow(() -> new ResourceAlreadyExistsException(
                            "role not found"
                    ));

            UserEntity user =
                    new UserEntity();

            user.setName(teacherRequestDto.name());
            user.setEmail(teacherRequestDto.email());
            user.setPassword(bCryptPasswordEncoder.encode(teacherRequestDto.password()));

            user.setRole(role);

            UserEntity savedUser =
                    userRepository.save(user);



            TeacherEntity teacher =
                    new TeacherEntity();

            teacher.setSpecialization(
                    teacherRequestDto.specialization()
            );
            //teacher.setRole(role);

            teacher.setUser(savedUser);

            teacher.setFaculty(faculty);

            teacher.setSubjects(subjects);

            TeacherEntity savedTeacher =
                    teacherRepository.save(teacher);

            return teacherMapper
                    .toResponseDto(savedTeacher);
        }


    @Override
public List<TeacherResponseDto> teacherList() {
        return teacherRepository.findAll().stream().map(
                teacherMapper::toResponseDto).toList();

    }


    @Override
    public String deleteTeacher(int id) {
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceAlreadyExistsException("Teacher not found with id " + id));
        UserEntity user=teacher.getUser();
        teacherRepository.delete(teacher);
        userRepository.delete(user);

        return "teacher deleted";
    }

    @Override
    public TeacherResponseDto updateTeacher(int id, TeacherRequestDto teacherRequestDto)
    {

        TeacherEntity teacher =
                teacherRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceAlreadyExistsException(
                                        "Teacher not found"
                                ));

        FacultyEntity faculty =
                facultyRepository.findById(
                        teacherRequestDto.facultyId()
                ).orElseThrow(() ->
                       new ResourceAlreadyExistsException(
                                "Faculty not found"
                        ));

        List<SubjectEntity> subjects =
                subjectRepository.findAllById(
                        teacherRequestDto.subjectIds()
               );
        if (subjects.size() != teacherRequestDto.subjectIds().size()) {
            throw new ResourceAlreadyExistsException(
                    "Some subjects not found"
            );
        }
        Role role=roleRepository.findByRoleName("TEACHER")
                .orElseThrow(()-> new ResourceAlreadyExistsException(
                        "role not found"
                ));

       UserEntity user=new UserEntity();
       user.setName(teacherRequestDto.name());
       user.setEmail(teacherRequestDto.email());
       if(teacherRequestDto.password()!=null && !teacherRequestDto.password().isBlank()) {


        user.setPassword(bCryptPasswordEncoder.encode(
                teacherRequestDto.password()
        ));
    }
       userRepository.save(user);
      teacher.setSpecialization((teacherRequestDto.specialization()));
      teacher.setFaculty(faculty);
      teacher.setSubjects(subjects);
      TeacherEntity updateTeacher=teacherRepository.save(teacher);
      return teacherMapper.toResponseDto(updateTeacher);
    }

    @Override
    public TeacherResponseDto getByID(int id) {
        TeacherEntity teacher =
                teacherRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceAlreadyExistsException(
                                        "Teacher not found"
                                ));

        return teacherMapper
                .toResponseDto(teacher);
    }
}
