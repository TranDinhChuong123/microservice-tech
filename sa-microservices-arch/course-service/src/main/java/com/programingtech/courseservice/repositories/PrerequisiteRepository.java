package com.programingtech.courseservice.repositories;

import com.programingtech.courseservice.models.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {
    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
}
