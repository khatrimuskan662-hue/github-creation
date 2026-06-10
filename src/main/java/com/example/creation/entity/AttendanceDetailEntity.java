package com.example.creation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AttendanceDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private Attendence attendance;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Enumerated(EnumType.STRING)
    private AttendenceStatus status;
}
