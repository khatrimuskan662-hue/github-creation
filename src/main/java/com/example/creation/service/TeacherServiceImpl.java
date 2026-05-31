package com.example.creation.service;

import com.example.creation.dto.request.TeacherRequestDto;
import com.example.creation.dto.response.TeacherResponseDto;
import com.example.creation.entity.*;
import com.example.creation.exception.ResourseAlreadyExistException;
import com.example.creation.exception.ResoursenotFoundException;
import com.example.creation.mapper.TeacherMapper;
import com.example.creation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
        boolean exist = userRepository.existsByEmail(teacherRequestDto.email());
        if (exist) {
            throw new ResourseAlreadyExistException(
                    "email already exist"
            );
        }
            FacultyEntity faculty =
                    facultyRepository.findById(
                            teacherRequestDto.facultyId()
                    ).orElseThrow(() ->
                            new ResoursenotFoundException(
                                    "Faculty not found"
                            ));
            List<SubjectEntity> subjects =
                    subjectRepository.findAllById(
                            teacherRequestDto.subjectIds()
                    );

            if (subjects.isEmpty()) {
                throw new ResoursenotFoundException(
                        "Subjects not found"
                );
            }
            if(subjects.size()!=teacherRequestDto.subjectIds().size()){
                throw new ResoursenotFoundException(
                        "some subject not found"
                );
            }
            Role role = roleRepository.findByRoleName("TEACHER")
                    .orElseThrow(() -> new ResoursenotFoundException(
                            "role not found"
                    ));

            UserEntity user =
                    new UserEntity();

            user.setName(teacherRequestDto.name());

            user.setEmail(teacherRequestDto.email());

            user.setPassword(
                    bCryptPasswordEncoder.encode(
                            teacherRequestDto.password()
                    )
            );

            user.setRole(role);

            UserEntity savedUser =
                    userRepository.save(user);

            TeacherEntity teacher =
                    new TeacherEntity();

            teacher.setSpecialization(
                    teacherRequestDto.specialization()
            );

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
                        new ResoursenotFoundException("Teacher not found with id " + id));
        UserEntity user=teacher.getUser();
        teacherRepository.delete(teacher);
        userRepository.delete(user);

        return "teacher deleted";
    }

    @Override
    public TeacherResponseDto updateTeacher(int id, TeacherRequestDto teacherRequestDto) {

        TeacherEntity teacher =
                teacherRepository.findById(id)
                        .orElseThrow(() ->
                                new ResoursenotFoundException(
                                        "Teacher not found"
                                ));

        FacultyEntity faculty =
                facultyRepository.findById(
                        teacherRequestDto.facultyId()
                ).orElseThrow(() ->
                        new ResoursenotFoundException(
                                "Faculty not found"
                        ));

        List<SubjectEntity> subjects =
                subjectRepository.findAllById(
                        teacherRequestDto.subjectIds()
                );
        if (subjects.size() != teacherRequestDto.subjectIds().size()) {
            throw new ResoursenotFoundException(
                    "Some subjects not found"
            );
        }

        UserEntity user =
                teacher.getUser();

        user.setName(teacherRequestDto.name());

        user.setEmail(teacherRequestDto.email());

        user.setPassword(
               bCryptPasswordEncoder.encode(
                        teacherRequestDto.password()
                )
        );

        userRepository.save(user);

        teacher.setSpecialization(
                teacherRequestDto.specialization()
        );

        teacher.setFaculty(faculty);

        teacher.setSubjects(subjects);

        TeacherEntity updatedTeacher =
                teacherRepository.save(teacher);

        return teacherMapper
                .toResponseDto(updatedTeacher);


    }

    @Override
    public TeacherResponseDto getByID(int id) {
        TeacherEntity teacher =
                teacherRepository.findById(id)
                        .orElseThrow(() ->
                                new ResoursenotFoundException(
                                        "Teacher not found"
                                ));

        return teacherMapper
                .toResponseDto(teacher);
    }
}
