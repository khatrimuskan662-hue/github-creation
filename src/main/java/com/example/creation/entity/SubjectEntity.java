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
     private String subjectName;

     @ManyToOne
    @JoinColumn(name = "semester_id")
    @JsonBackReference
    private SemesterEntity semester;

     @ManyToMany(mappedBy = "subjects")
    // @JoinColumn(name = "teacherId")
    private List<TeacherEntity> teachers;
}
