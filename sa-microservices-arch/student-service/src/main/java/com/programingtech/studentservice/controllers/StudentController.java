package com.programingtech.studentservice.controllers;
import org.json.simple.*;
import org.json.simple.parser.*;
import com.programingtech.studentservice.client.AuthenticationServiceClient;
import com.programingtech.studentservice.models.Student;
import com.programingtech.studentservice.payloadRequest.TokenRequest;
import com.programingtech.studentservice.services.StudentService;
import com.programingtech.studentservice.services.TokenValidationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class StudentController {

    private final StudentService studentService;
    private final AuthenticationServiceClient authenticationServiceClient;
    private final TokenValidationService tokenValidationService;

    // Endpoint để lấy tất cả sinh viên
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

//    / Endpoint để lấy thông tin của một sinh viên dựa trên ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
//    @GetMapping("/userid-token")
//    public ResponseEntity<Student> findStudentById(@RequestBody TokenRequest tokenRequest) {
//        Long userId  = authenticationServiceClient.getUseIdTokenInfo(tokenRequest);
//
//
//            Optional<Student> student = studentService.getStudentById(userId);
//            return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }


//    @GetMapping("/userid-token")
//    public ResponseEntity<Student> findStudentById(@RequestBody TokenRequest tokenRequest) {
//        ResponseEntity<Long> response = authenticationServiceClient.getUseIdTokenInfo(tokenRequest);
//        if(response.getStatusCode().is2xxSuccessful()) {
//            Long userId = response.getBody();
//            Optional<Student> student = studentService.getStudentById(userId);
//            return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }


    @GetMapping("/find")
    public Student findStudentById(@RequestHeader("Authorization") String authorizationHeader) {
        Long userId = authenticationServiceClient.getUseIdTokenInfo(authorizationHeader);
        return studentService.getStudentById(userId).get();
    }



//    @GetMapping("/find")
//    public Student findStudentById(@RequestParam Long id) {
//        return studentService.getStudentById(id).get();
//    }




    @GetMapping("/validateStudent/{id}")
    public boolean validateStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.isPresent();
    }



    // Endpoint để tạo mới hoặc cập nhật thông tin của một sinh viên
    @PostMapping
    public ResponseEntity<Student> createOrUpdateStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveOrUpdateStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Endpoint để xóa thông tin của một sinh viên dựa trên ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Các endpoint khác có thể được thêm vào tùy theo yêu cầu của ứng dụng của bạn
}
