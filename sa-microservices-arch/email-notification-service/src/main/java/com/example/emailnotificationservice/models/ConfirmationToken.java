package com.example.emailnotificationservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {
    private String token;
    private String email;
    private Date createdAt;
    private Date expiresAt;

    // Constructors, getters, and setters
}