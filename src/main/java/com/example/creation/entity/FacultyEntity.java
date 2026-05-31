package com.example.creation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class FacultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String facultyName;

    @OneToMany(mappedBy = "faculty")
    @JsonManagedReference
    private List<SemesterEntity> semester;

    @OneToMany(mappedBy = "faculty")
    private List<TeacherEntity> teacher;

    @OneToMany(mappedBy = "faculty")
    private List<StudentEntity> student;

}
