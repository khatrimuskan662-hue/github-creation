package com.example.creation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Courses {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String title;

        private String duration;

        @ManyToOne
        @JoinColumn(name = "teacher_id")
        @JsonBackReference
        private TeacherEntity teacher;
}
