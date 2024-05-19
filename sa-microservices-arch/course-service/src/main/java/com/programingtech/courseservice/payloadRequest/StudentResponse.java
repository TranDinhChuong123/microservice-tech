package com.programingtech.courseservice.payloadRequest;

import com.programingtech.courseservice.models.Major;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentResponse  {
    private Long id;
    private String name;
    private String email;
    private Major major;
    private int creditsCompleted;
    private double gpa;
    private String status;
}
