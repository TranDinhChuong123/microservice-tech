package com.programingtech.courseservice.models;//package com.programingtechie.coursemanagementservice.models;
//
//import jakarta.persistence.*;
//
//@Entity
//public class StudentCourseRegistration {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student; // Sinh viên
//
//
//
//    @ManyToOne
//    @JoinColumn(name = "class_id", nullable = false)
//    private ClassRoom classRoom; // Lớp học
//
//    private boolean confirmed; // Đã xác nhận đăng ký hay chưa
//
//    // Constructors, getters, setters
//}
//
