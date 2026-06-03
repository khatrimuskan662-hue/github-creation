package com.example.creation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String roleName;

    @OneToMany(mappedBy = "role") //
                        //userentity already owns relationship
    private List<UserEntity> user;

    @OneToMany
    private List<TeacherEntity> teacher;
}
