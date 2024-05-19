package com.programingtech.studentservice.repositories;


import com.programingtech.studentservice.models.Class;
import com.programingtech.studentservice.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
}

