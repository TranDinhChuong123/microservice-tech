package com.programingtech.courseservice.services;


import com.programingtech.courseservice.client.StudentServiceClient;
import com.programingtech.courseservice.models.Course;
import com.programingtech.courseservice.models.Student;
import com.programingtech.courseservice.payloadRequest.StudentResponse;
import com.programingtech.courseservice.payloadRequest.TokenRequest;
import com.programingtech.courseservice.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final StudentServiceClient studentServiceClient;
    private final CourseRepository courseRepository;

    public List<Course> findCoursesByMajorsId(@RequestHeader("Authorization") String authorizationHeader) {
        Student student =  studentServiceClient.findStudentById(authorizationHeader);
        List<Course> courseList= courseRepository.findCoursesByMajorId(student.getMajor().getId());
        return courseList;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void saveAllCourses(List<Course> courses) {
        courseRepository.saveAll(courses);
    }
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
}
