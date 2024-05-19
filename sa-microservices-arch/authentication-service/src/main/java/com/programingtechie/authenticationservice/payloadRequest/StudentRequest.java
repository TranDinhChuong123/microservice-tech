package com.programingtechie.authenticationservice.payloadRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest  {
    private Long id;
    private String name;
    private String email;
    private String facultyName;
    private String majorName;
    private List<String> enrolledCourses;
    private int creditsCompleted;
    private double gpa;
    private String status;
}

