package com.programingtech.studentservice;

import com.programingtech.studentservice.models.Faculty;
import com.programingtech.studentservice.models.Major;
import com.programingtech.studentservice.models.Student;
import com.programingtech.studentservice.services.FacultyService;
import com.programingtech.studentservice.services.MajorService;
import com.programingtech.studentservice.services.StudentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RequiredArgsConstructor
public class StudentServiceApplication {

    private final StudentService studentService;
    private final MajorService majorService;
    private final FacultyService facultyService;

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }
    @PostConstruct
    public void init() {
        // Tạo một khoa và lưu vào cơ sở dữ liệu
        Faculty faculty = Faculty.builder()
                .name("Khoa Công nghê thông tin")
                .build();
        facultyService.saveFaculty(faculty);

        // Tạo một ngành học và lưu vào cơ sở dữ liệu
        Major major = Major.builder()
                .name("Kỹ Thuật phần mềm")
                .faculty(faculty)  // Liên kết với khoa vừa tạo
                .build();
        majorService.saveMajor(major);

        Major major1 = Major.builder()
                .name("Kỹ Thuật phần mềm")
                .faculty(faculty)  // Liên kết với khoa vừa tạo
                .build();
        majorService.saveMajor(major1);

        // Tạo một sinh viên và lưu vào cơ sở dữ liệu
        Student student = Student.builder()
                .id(20002835L)
                .gpa(4)
                .email("trandinhchuong12c2mk@gmail.com")
                .name("Trần Trường Sinh")
                .status("Succeeded")
                .creditsCompleted(1)
//                .faculty(faculty)  // Liên kết với khoa
                .major(major)      // Liên kết với ngành
                .build();
        studentService.saveOrUpdateStudent(student);

        Student student2 = Student.builder()
                .id(20038722L)
                .gpa(4)
                .email("tranbinhan@gmail.com")
                .name("Trần Bình An ")
                .status("Succeeded")
                .creditsCompleted(1)
//                .faculty(faculty)  // Liên kết với khoa
                .major(major)      // Liên kết với ngành
                .build();
        studentService.saveOrUpdateStudent(student2);
    }

}
