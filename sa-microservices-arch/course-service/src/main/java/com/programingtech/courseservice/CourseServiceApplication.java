package com.programingtech.courseservice;

import com.programingtech.courseservice.models.*;
import com.programingtech.courseservice.models.Class;
import com.programingtech.courseservice.services.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RequiredArgsConstructor
public class CourseServiceApplication {

    private final CourseService courseService;
    private final MajorService majorService;
    private final FacultyService facultyService;
    private final LecturerService lecturerService;
    private final RoomService roomService;
    private final ClassService classService;
    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

    private Long generateUniqueId() {
        return ThreadLocalRandom.current().nextLong(100000, 1000000); // Sinh số ngẫu nhiên từ 100,000 đến 999,999
    }

    @PostConstruct
    public void init() {
        // Tạo một khoa và lưu vào cơ sở dữ liệu
        Faculty faculty = Faculty.builder()
                .name("Khoa Công nghệ thông tin")
                .description("Mô tả về khoa")
                .build();
        facultyService.saveFaculty(faculty);

        // Tạo danh sách môn học
        List<Course> courses = new ArrayList<>();
        String[] tenMonHoc = {
                "Chủ nghĩa xã hội khoa học",
                "Công nghệ phần mềm",
                "Đảm bảo chất lượng và Kiểm thử phần mềm",
                "Giáo dục quốc phòng và an ninh 1",
                "Giáo dục quốc phòng và an ninh 2",
                "Giáo dục thể chất 1",
                "Giáo dục thể chất 2",
                "Giao tiếp kinh doanh",
                "Hệ cơ sở dữ liệu",
                "Hệ quản trị cơ sở dữ liệu",
                "Hệ quản trị cơ sở dữ liệu NoSQL MongoDB",
                "Hệ thống Máy tính",
                "Hệ Thống và Công nghệ Web",
                "Kinh tế chính trị Mác - Lênin",
                "Kỹ năng làm việc nhóm",
                "Kỹ thuật lập trình",
                "Lập trình hướng đối tượng",
                "Lập trình hướng sự kiện với công nghệ Java",
                "Lập trình phân tán với công nghệ Java",
                "Lập trình phân tích dữ liệu 1",
                "Lập trình thiết bị di động",
                "Lập trình WWW (Java)",
                "Lịch sử Đảng Cộng sản Việt Nam",
                "Lý thuyết đồ thị",
                "Mạng máy tính",
                "Mô hình hóa dữ liệu NoSQL MongoDB",
                "Nhập môn an toàn thông tin",
                "Nhập môn Lập trình",
                "Nhập môn Tin học",
                "Những vấn đề xã hội và đạo đức nghề nghiệp",
                "Phân tích thiết kế hệ thống",
                "Pháp luật đại cương",
                "Phát triển ứng dụng",
                "Phương pháp luận nghiên cứu khoa học",
                "Phương pháp tính",
                "Tâm lý học đại cương",
                "Thống kê máy tính và ứng dụng",
                "Toán cao cấp 1",
                "Toán cao cấp 2",
                "Triết học Mác - Lênin"
        };

        List<Lecturer> lecturers = new ArrayList<>();
        Lecturer lecturer1 = Lecturer.builder()
                .fullName("Nguyễn Văn A")
                .email("nguyenvana@example.com")
                .department("Khoa Công nghệ thông tin")
                .build();
        Lecturer lecturer2 = Lecturer.builder()
                .fullName("Trần Thị B")
                .email("tranthib@example.com")
                .department("Khoa Công nghệ thông tin")
                .build();
        lecturers.add(lecturer1);
        lecturers.add(lecturer2);

        lecturerService.saveLecturer(lecturer1);
        lecturerService.saveLecturer(lecturer2);



        List<Room> rooms = new ArrayList<>();
        Room room1 = Room.builder()
                .roomNumber("101")
                .building("A1")
                .build();
        Room room2 = Room.builder()
                .roomNumber("201")
                .building("B2")
                .build();
        rooms.add(room1);
        rooms.add(room2);

        roomService.saveRoom(room1);
        roomService.saveRoom(room2);

        Random random = new Random();
        for (String ten : tenMonHoc) {
            Course course = Course.builder()
                    .name(ten)
                    .description("Mô tả về " + ten)
                    .credits(random.nextInt(2) + 3) // Có thể điều chỉnh số tín chỉ tùy theo yêu cầu
                    .isElective(false) // Hoặc true nếu môn học là môn tự chọn
                    .build();
            courses.add(course);
        }

        courseService.saveAllCourses(courses);
        int count = 0;
        for (Course course : courses) {
            String[] tenLopHoc = {
                    "DHKTPM16A",
                    "DHKTPM16B",
                    "DHKTPM12C",
                    "DHKHMT16A",
                    "DHKHMT13B",
                    "DHKHDL15A",
                    "DHKHDL17B",
                    "DHKHDL16C",
                    "DHQTKD18C",
                    "DHQDDS12C",
                    "DHQTKD12C",
                    "DHQTKQE12",
                    "DHQADD112",
                    "DHQKTN121",

            };

            Set<Long> generatedIds = new HashSet<>();
            for(String lopHoc: tenLopHoc){
                Long generatedId;
                do {
                    generatedId = generateUniqueId();
                } while (!generatedIds.add(generatedId));

                Class newClass = new Class();
                newClass.setClassName(lopHoc);
                newClass.setId(generatedId);

                int minFee = 3000000; // 3 triệu
                int maxFee = 9999990; // 9 triệu 990 nghìn
                int randomFee = random.nextInt(maxFee - minFee + 1) + minFee;
                randomFee = (randomFee / 10) * 10;

                newClass.setRegistrationFee(randomFee); // Giả sử học phí là 100 đơn vị
                newClass.setSemester("2024 Spring"); // Giả sử là học kỳ Xuân 2024
                newClass.setMaxStudents(random.nextInt(100) + 50); // Số lượng học sinh tối đa từ 10 đến 40
                newClass.setStartDate(LocalDate.of(2024, 2, 1)); // Giả sử bắt đầu học vào ngày 1/2/2024
                newClass.setEndDate(LocalDate.of(2024, 5, 31)); // Giả sử kết thúc học vào ngày 31/5/2024

                // Chọn ngẫu nhiên một giảng viên và phòng học cho lớp học
                Lecturer randomLecturer = lecturers.get(random.nextInt(lecturers.size()));
                Room randomRoom = rooms.get(random.nextInt(rooms.size()));

                newClass.setLecturer(randomLecturer);
                newClass.setRoom(randomRoom);

                // Thiết lập môn học cho lớp học
                newClass.setCourse(course);

                // Tạo danh sách học sinh đăng ký và danh sách học sinh đợi
//            Set<Long> enrolledStudents = new HashSet<>();
//            Set<Long> waitlistStudents = new HashSet<>();
                // Code để thêm học sinh vào danh sách enrolledStudents và waitlistStudents
                // ...

//            newClass.setEnrolledStudents(enrolledStudents);
//            newClass.setWaitlistStudents(waitlistStudents);

                classService.saveClass(newClass);
            };





        }



        // Tạo danh sách môn học tự chọn
        List<Course> electiveCourses = new ArrayList<>();
        Course electiveCourse1 = Course.builder()
                .name("Môn học tự chọn 1")
                .description("Mô tả về môn học tự chọn 1")
                .credits(3)
                .isElective(true)
                .build();
        electiveCourses.add(electiveCourse1);
        // Thêm các môn học tự chọn khác vào danh sách

        // Lưu danh sách môn học tự chọn vào cơ sở dữ liệu
        courseService.saveAllCourses(electiveCourses);

        // Tạo một ngành học và lưu vào cơ sở dữ liệu
        Major major = Major.builder()
                .name("Kỹ thuật phần mềm")
                .description("Mô tả về ngành Kỹ thuật phần mềm")
                .faculty(faculty)
                .courses(courses)
                .electiveCourses(electiveCourses)
                .build();
        majorService.saveMajor(major);
    }
}
