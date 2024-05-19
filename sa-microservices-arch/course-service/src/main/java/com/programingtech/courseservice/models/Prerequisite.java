package com.programingtech.courseservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prerequisites")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prerequisite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // Môn học cơ sở

    @ManyToOne
    @JoinColumn(name = "prerequisite_id", nullable = false)
    private Course prerequisiteCourse; // Môn học tiên quyết

    // Constructors, getters, setters
}
