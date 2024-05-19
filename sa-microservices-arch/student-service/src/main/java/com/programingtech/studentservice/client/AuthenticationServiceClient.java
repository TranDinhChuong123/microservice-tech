package com.programingtech.studentservice.client;//package com.programingtechie.coursemanagementservice.client;


import com.programingtech.studentservice.payloadRequest.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "AUTHENTICATION-SERVICE", url = "http://localhost:8088/api/v1/auth")
public interface AuthenticationServiceClient  {

    @GetMapping ("/userid-info")
    public Long getUseIdTokenInfo(@RequestHeader("Authorization") String authorizationHeader);
}
