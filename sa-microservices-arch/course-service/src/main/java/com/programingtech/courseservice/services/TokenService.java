package com.programingtech.courseservice.services;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Loại bỏ "Bearer " từ header
        }
        return null;
    }
}
