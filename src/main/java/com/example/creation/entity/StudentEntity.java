package com.example.creation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyEntity faculty;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private SemesterEntity semester;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;
}
