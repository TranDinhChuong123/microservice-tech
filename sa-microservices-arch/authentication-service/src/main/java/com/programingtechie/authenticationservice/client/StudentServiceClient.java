package com.programingtechie.authenticationservice.client;//package com.programingtechie.coursemanagementservice.client;


import com.programingtechie.authenticationservice.payloadRequest.StudentRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "STUDENT-SERVICE", url = "http://localhost:8082/api/v1/students")
public interface StudentServiceClient {

    @GetMapping("/find/{id}")
    public StudentRequest findStudentById(@PathVariable Long id) ;

    @GetMapping("/validateStudent/{id}")
    public boolean validateStudent(@PathVariable Long id);


}
