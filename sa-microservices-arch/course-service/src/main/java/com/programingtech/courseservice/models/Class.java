package com.programingtech.courseservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "class")
public class Class {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Thông tin về tên lớp
    private String className;
    private double registrationFee; // học phí
    private String semester; // học kỳ
    private int maxStudents;
    private LocalDate startDate; // Thông tin về ngày bắt đầu học
    private LocalDate endDate; // Thông tin về ngày kết thúc học

    // Thông tin về giảng viên
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    // Thông tin về phòng học
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ElementCollection
    @CollectionTable(name = "enrolled_students", joinColumns = @JoinColumn(name = "class_id"))
    @Column(name = "student_id")
    private Set<Long> enrolledStudents;


//    @ElementCollection
//    @CollectionTable(name = "waitlist_students", joinColumns = @JoinColumn(name = "class_id"))
//    @Column(name = "student_id")
//    private Set<Long> waitlistStudents;

    // Getters and Setters
}
