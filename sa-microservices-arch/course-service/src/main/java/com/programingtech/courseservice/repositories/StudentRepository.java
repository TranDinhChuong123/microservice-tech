package com.programingtech.courseservice.repositories;


import com.programingtech.courseservice.models.Course;
import com.programingtech.courseservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT c FROM Course c JOIN c.majors m WHERE m.id = :majorId")
    List<Course> findCoursesByMajorId(@Param("majorId") Long majorId);
    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
}
