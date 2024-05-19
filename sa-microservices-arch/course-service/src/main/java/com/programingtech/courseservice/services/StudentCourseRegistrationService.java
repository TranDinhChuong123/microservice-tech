package com.programingtech.courseservice.services;//package com.programingtechie.coursemanagementservice.services;
//
//import com.programingtechie.coursemanagementservice.models.StudentCourseRegistration;
//import com.programingtechie.coursemanagementservice.repositories.StudentCourseRegistrationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class StudentCourseRegistrationService {
//
//    @Autowired
//    private StudentCourseRegistrationRepository registrationRepository;
//
//    public List<StudentCourseRegistration> getAllRegistrations() {
//        return registrationRepository.findAll();
//    }
//
//    public StudentCourseRegistration saveRegistration(StudentCourseRegistration registration) {
//        return registrationRepository.save(registration);
//    }
//
//    public void deleteRegistration(Long registrationId) {
//        registrationRepository.deleteById(registrationId);
//    }
//
//    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
//}
