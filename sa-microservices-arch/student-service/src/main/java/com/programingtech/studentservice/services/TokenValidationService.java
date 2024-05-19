package com.programingtech.studentservice.services;

import com.programingtech.studentservice.client.AuthenticationServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {

    @Autowired
    private AuthenticationServiceClient authService;

//    public ResponseEntity<?> decodeToken(String token) {
//        return authService.decodeToken(token);
//    }
}
