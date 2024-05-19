package com.programingtech.courseservice.repositories;


import com.programingtech.courseservice.models.Class;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
    List<Class> findAllByCourse_Id(Long courseId);
    @Query("SELECT c FROM Class c JOIN c.enrolledStudents e WHERE e = :studentId")
    List<Class> findAllByEnrolledStudentsContains(@Param("studentId") Long studentId);

}

