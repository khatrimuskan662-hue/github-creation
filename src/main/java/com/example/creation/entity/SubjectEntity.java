package com.example.creation.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
     private String subjectName;

     @ManyToOne
    @JoinColumn(name = "semester_id")
    @JsonBackReference
    private SemesterEntity semester;


    @ManyToMany(mappedBy = "subjects")
    private List<TeacherEntity> teachers;

     //@ManyToOne
    //@JoinColumn(name = "teacherId")
    //private List<TeacherEntity> teacher;

    //@ManyToOne
    //@JoinColumn(name = "faculty_id")
    //@JsonBackReference
    //private FacultyEntity faculty;

    //@OneToMany(mappedBy = "faculty")
    //private List<SubjectEntity> subjects;
}
