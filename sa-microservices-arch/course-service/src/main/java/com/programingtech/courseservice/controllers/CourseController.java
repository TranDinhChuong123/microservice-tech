package com.programingtech.courseservice.controllers;


import com.programingtech.courseservice.models.Course;
import com.programingtech.courseservice.payloadRequest.TokenRequest;
import com.programingtech.courseservice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
@CrossOrigin(allowedHeaders = "*", origins ="*" )
public class CourseController {


    private final CourseService courseService;

    @GetMapping ("/majorid")
    public ResponseEntity<List<Course>> findCoursesByMajorsId(@RequestHeader("Authorization") String authorizationHeader){
        List<Course> courses = courseService.findCoursesByMajorsId(authorizationHeader);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


//    @PostMapping ("/test")
//    public ResponseEntity<String> findCoursesByMajorsId(@RequestHeader("Authorization") String authorizationHeader){
//        // Kiểm tra xem header "Authorization" có chứa token không
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            // Lấy token bằng cách loại bỏ phần "Bearer " từ header
//            String token = authorizationHeader.substring(7); // Loại bỏ "Bearer " từ header
//            // Sử dụng token ở đây để lấy thông tin cần thiết
//
//            return new ResponseEntity<>(token, HttpStatus.OK);
//
//        } else {
//            // Trả về lỗi nếu header "Authorization" không hợp lệ
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }


    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, course != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<Course>> findCoursesByFacultyId(@PathVariable("id") Long courseId) {
//        Course course = courseService.getCourseById(courseId);
//        return new ResponseEntity<>(course, course != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
//    }


    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
