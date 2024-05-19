package com.programingtech.studentservice.models;

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

    @OneToMany(mappedBy = "course")
    private Set<Class> classes;

    // Getters and Setters
}


