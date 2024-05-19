package com.programingtech.courseservice.repositories;



import com.programingtech.courseservice.models.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    List<Major> findByCoursesId(Long courseId);
}

