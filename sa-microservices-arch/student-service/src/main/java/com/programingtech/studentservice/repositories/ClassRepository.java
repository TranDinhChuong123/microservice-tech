package com.programingtech.studentservice.repositories;


import com.programingtech.studentservice.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
}

