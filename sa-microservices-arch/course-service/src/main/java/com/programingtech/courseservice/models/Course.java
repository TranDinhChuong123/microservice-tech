package com.programingtech.courseservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")

public class Course {

//    Thuộc tính: ID, Tên môn học, Số tín chỉ, Mô tả, Môn học tiên quyết.
//    Mối quan hệ: Mỗi môn học có thể có nhiều môn học tiên quyết (N:N với Course).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int credits;
    private boolean isElective;

    @ManyToMany
    @JoinTable(
            name = "course_prerequisites", // môn học tiên quyết cho 1 môn học khác
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private Set<Course> prerequisites;

//    @OneToMany(mappedBy = "course")
//    private Set<Class> classes;
//
    @ManyToMany(mappedBy = "courses")
    private Set<Major> majors;
//
//
//    @ManyToMany(mappedBy = "electiveCourses")
//    private Set<Major> electiveForMajors;


    // Getters and Setters
}


