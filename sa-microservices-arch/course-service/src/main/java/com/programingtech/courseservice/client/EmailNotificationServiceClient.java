package com.programingtech.courseservice.client;//package com.programingtechie.coursemanagementservice.client;


import com.programingtech.courseservice.models.Student;
import com.programingtech.courseservice.payloadRequest.EmailMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "EMAIL-NOTIFICATION-SERVICE", url = "http://localhost:8083/api/v1/emails")
public interface EmailNotificationServiceClient {

    @PostMapping("/new")
    public EmailMessage create(@RequestBody EmailMessage emailMessage);

}