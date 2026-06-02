package com.example.creation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TeacherassignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private TeacherEntity teacher;

    @ManyToOne
    private SubjectEntity subject;

    @ManyToOne
    private SemesterEntity semester;
}
