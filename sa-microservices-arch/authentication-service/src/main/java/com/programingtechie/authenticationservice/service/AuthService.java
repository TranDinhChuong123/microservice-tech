package com.programingtechie.authenticationservice.service;

import com.programingtechie.authenticationservice.client.StudentServiceClient;
import com.programingtechie.authenticationservice.entity.Account;
import com.programingtechie.authenticationservice.payloadRequest.StudentRequest;
import com.programingtechie.authenticationservice.repository.AccountRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final StudentServiceClient studentServiceClient;
    private final JwtService jwtService;

    public boolean doesStudentExist(Long studentId) {
        return studentServiceClient.validateStudent(studentId) ;
    }


    public Account saveAccount(Account credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        return repository.save(credential);

    }

    public Optional<Account> getAccountOptional(String username){
        return repository.findByUsername(username);
    }


    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public Claims decodeToken(String token) {
        return jwtService.decodeToken(token);
    }


    public StudentRequest findStudentById(Long userID) {
        return studentServiceClient.findStudentById(userID);
    }
}
