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

    @Column(unique = true)//prevents duplicate
    private String facultyName;

    @OneToMany(mappedBy = "faculty")
    @JsonManagedReference //
    private List<SemesterEntity> semesters;

    @OneToMany(mappedBy = "faculty")
    private List<TeacherEntity> teachers;

    @OneToMany(mappedBy = "faculty")
    private List<StudentEntity> students;

}
