package com.programingtech.courseservice.repositories;


import com.programingtech.courseservice.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c JOIN c.majors m WHERE m.id = :majorId")
    List<Course> findCoursesByMajorId(@Param("majorId") Long majorId);


}
