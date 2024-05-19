package com.programingtech.studentservice.repositories;



import com.programingtech.studentservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
}
