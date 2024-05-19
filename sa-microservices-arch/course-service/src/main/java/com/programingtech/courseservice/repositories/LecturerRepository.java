package com.programingtech.courseservice.repositories;


import com.programingtech.courseservice.models.Class;
import com.programingtech.courseservice.models.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}

