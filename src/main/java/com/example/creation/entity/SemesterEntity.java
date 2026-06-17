package com.example.creation.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.security.auth.Subject;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SemesterEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String semesterName;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonBackReference
    private FacultyEntity faculty;



    @OneToMany(mappedBy = "semester")
    @JsonManagedReference
    private List<SubjectEntity> subjects;

   // @OneToMany(mappedBy = "semester")
    //private List<StudentEntity> students;

    //@OneToMany(mappedBy = "semester")
    //private List<TeacherEntity> teachers;
}


