package com.programingtech.courseservice.client;//package com.programingtechie.coursemanagementservice.client;


import com.programingtech.courseservice.models.Student;
import com.programingtech.courseservice.payloadRequest.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@FeignClient(name = "STUDENT-SERVICE", url = "http://localhost:8082/api/v1/students")
public interface StudentServiceClient  {

    @GetMapping("/find")
    public Student findStudentById(@RequestHeader("Authorization") String authorizationHeader);
}
