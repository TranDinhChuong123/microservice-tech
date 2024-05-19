package com.programingtechie.authenticationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programingtechie.authenticationservice.dto.AuthRequest;
import com.programingtechie.authenticationservice.entity.Account;
import com.programingtechie.authenticationservice.payloadRequest.StudentRequest;
import com.programingtechie.authenticationservice.payloadRequest.TokenRequest;
import com.programingtechie.authenticationservice.response.AuthResponse;
import com.programingtechie.authenticationservice.service.AuthService;
import com.programingtechie.authenticationservice.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);


    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final AuthService authService;


    @GetMapping ("/userid-info")
    public Long getUseIdTokenInfo(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // Lấy token bằng cách loại bỏ phần "Bearer " từ header
                String token = authorizationHeader.substring(7);
                Claims claims = authService.decodeToken(token);
                String userIdString = claims.get("sub", String.class);
                Long userId = Long.valueOf(userIdString);
                return userId;
            }
        } catch (Exception e) {
            logger.error("Error decoding token: {}", e.getMessage());
            return 0L;  // Token kh
        }
        return 0L;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                                            authRequest.getUsername(),
                                            authRequest.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User: " + userDetails.getUsername());

        String token = authService.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> addNewAccount(@RequestBody Account account) {
//        if (authService.doesStudentExist(account.getUsername())) {
//            authService.saveAccount(account);
//            return ResponseEntity.status(HttpStatus.OK).body("Saved username successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student does not exist");
//        }
//    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }


    @PostMapping("/getuserfromtoken")
    public ResponseEntity<?> getUserInfoFromToken(@RequestBody String token) {
        try {
            Claims claims = authService.decodeToken(token);
            logger.info("Decoded token: {}", claims.toString());

            String username = (String) claims.get("sub");
            Account account = authService.getAccountOptional(username).get();

            logger.info("Retrieved account: {}", account);


            return ResponseEntity.ok().body(authService.findStudentById(1L)); // Trả về thông tin của token
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");  // Token không hợp lệ hoặc đã hết hạn
        }
    }


}
