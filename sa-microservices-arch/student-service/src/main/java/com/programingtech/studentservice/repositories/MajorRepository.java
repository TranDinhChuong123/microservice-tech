package com.programingtech.studentservice.repositories;


import com.programingtech.studentservice.models.Class;
import com.programingtech.studentservice.models.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
}

