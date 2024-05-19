package com.programingtech.courseservice.controllers;



import com.programingtech.courseservice.client.EmailNotificationServiceClient;
import com.programingtech.courseservice.client.StudentServiceClient;
import com.programingtech.courseservice.models.Class;
import com.programingtech.courseservice.models.Course;
import com.programingtech.courseservice.models.Student;
import com.programingtech.courseservice.payloadRequest.EmailMessage;
import com.programingtech.courseservice.payloadRequest.TokenRequest;
import com.programingtech.courseservice.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/classes")
@CrossOrigin(allowedHeaders = "*", origins ="*" )
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;
    private final StudentServiceClient studentServiceClient ;
    private final EmailNotificationServiceClient emailNotificationServiceClient;

    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PostMapping("/rm-cls-idstd/{classId}")
    public ResponseEntity<String> removeStudentFromClass(@PathVariable Long classId,@RequestHeader("Authorization") String authorizationHeader) {
        // Kiểm tra xem lớp học có tồn tại không
        Student student = studentServiceClient.findStudentById(authorizationHeader);
        if (!classService.existsById(classId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found");
        }

        // Lấy thông tin lớp học từ cơ sở dữ liệu
        Class targetClass = classService.findById(classId).orElse(null);
        if (targetClass == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found");
        }

        // Xóa sinh viên khỏi mảng enrolledStudents của lớp học
        targetClass.getEnrolledStudents().remove(student.getId());
        classService.saveClass(targetClass);

        return ResponseEntity.ok("Student removed from class successfully");
    }

    @GetMapping("/classes-by-enrolled-students")
    public ResponseEntity<List<Class>> findAllByEnrolledStudentsContains(@RequestHeader("Authorization") String authorizationHeader) {
        Student student = studentServiceClient.findStudentById(authorizationHeader);
        List<Class> classes = classService.findAllByEnrolledStudentsContains(student.getId());
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
    @GetMapping("/cls-enroll-std/{classId}")
    public ResponseEntity<Boolean> checkIfStudentEnrolledInClass(
            @PathVariable Long classId,@RequestHeader("Authorization") String authorizationHeader
    ) {
        Student student = studentServiceClient.findStudentById(authorizationHeader);

        if (!classService.existsById(classId)) {
            return ResponseEntity.notFound().build();
        }

        // Lấy thông tin lớp học từ ID
        Class courseClass = classService.findById(classId).orElse(null);
        if (courseClass == null) {
            return ResponseEntity.notFound().build();
        }

        // Kiểm tra xem studentId có trong danh sách enrolledStudents hay không
        boolean isEnrolled = courseClass.getEnrolledStudents().contains(student.getId());
        return ResponseEntity.ok(isEnrolled);
    }
    @GetMapping ("/{classId}/enroll")
    public ResponseEntity<?> enrollStudent(@PathVariable Long classId, @RequestHeader("Authorization") String authorizationHeader) {
        Student student = studentServiceClient.findStudentById(authorizationHeader);
        Optional<Class> optionalClass = classService.findById(classId);
        if (optionalClass.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Class clazz = optionalClass.get();
        // Kiểm tra xem lớp học đã đầy chưa
        if (clazz.getEnrolledStudents().size() >= clazz.getMaxStudents()) {
            return ResponseEntity.badRequest().body("Lớp học đã đầy.");
        }

        // Kiểm tra xem sinh viên đã đăng ký chưa
        if (clazz.getEnrolledStudents().contains(student.getId())) {
            return ResponseEntity.badRequest().body("Sinh viên đã đăng ký lớp học này.");
        }
        clazz.getEnrolledStudents().add(student.getId());
        classService.saveClass(clazz);

        EmailMessage emailMessage = EmailMessage.builder()
                        .to(student.getEmail())
                        .toName(student.getName())
                        .subjectCourses(clazz.getCourse().getName())
                .build();

        emailNotificationServiceClient.create(emailMessage);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/major-id")
    public ResponseEntity<List<Class>> findClassesByMajorsId(@RequestHeader("Authorization") String authorizationHeader){
        List<Class> classes = classService.findClassesByMajorsId(authorizationHeader);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/course-id/{courseId}")
    public ResponseEntity<List<Class>> findClassesByCourseId(@PathVariable("courseId") Long courseId){
        List<Class> classes = classService.findClassesByCourseId(courseId);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable("id") Long classId) {
        Class classObj = classService.getClassById(classId);
        return new ResponseEntity<>(classObj, classObj != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Class> createClass(@RequestBody Class classObj) {
        Class savedClass = classService.saveClass(classObj);
        return new ResponseEntity<>(savedClass, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable("id") Long classId) {
        classService.deleteClass(classId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
