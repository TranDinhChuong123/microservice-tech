package com.programingtech.courseservice.services;



import com.programingtech.courseservice.models.Class;
import com.programingtech.courseservice.models.Course;
import com.programingtech.courseservice.payloadRequest.TokenRequest;
import com.programingtech.courseservice.repositories.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;
    private final CourseService courseService;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Class getClassById(Long id) {
        return classRepository.findById(id).orElse(null);
    }

    public Class saveClass(Class clazz) {
        return classRepository.save(clazz);
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }

    public List<Class> findClassesByMajorsId(@RequestHeader("Authorization") String authorizationHeader) {
        List<Course> courses = courseService.findCoursesByMajorsId(authorizationHeader);
        List<Class> classes = new ArrayList<Class>();
        for (Course course : courses) {
            classes.addAll(classRepository.findAllByCourse_Id(course.getId()));
        }
        return classes;
    }

    public List<Class> findClassesByCourseId(Long courseId) {
        return classRepository.findAllByCourse_Id(courseId);
    }

    public Optional<Class> findById(Long classId) {
        return classRepository.findById(classId);
    }

    public boolean existsById(Long classId) {
        return classRepository.existsById(classId);
    }

    public List<Class> findAllByEnrolledStudentsContains(Long studentId) {
        return classRepository.findAllByEnrolledStudentsContains(studentId);
    }
}

