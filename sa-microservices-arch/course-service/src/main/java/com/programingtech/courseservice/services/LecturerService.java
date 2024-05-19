package com.programingtech.courseservice.services;

import com.programingtech.courseservice.models.Faculty;
import com.programingtech.courseservice.models.Lecturer;
import com.programingtech.courseservice.repositories.FacultyRepository;
import com.programingtech.courseservice.repositories.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import com.programingtech.courseservice.models.Faculty;
import com.programingtech.courseservice.models.Lecturer;
import com.programingtech.courseservice.repositories.LecturerRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.programingtech.courseservice.models.Lecturer;
import com.programingtech.courseservice.repositories.LecturerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LecturerService {
    private final LecturerRepository lecturerRepository;

    public Lecturer saveLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Optional<Lecturer> getLecturerById(Long id) {
        return lecturerRepository.findById(id);
    }

    public void deleteLecturerById(Long id) {
        lecturerRepository.deleteById(id);
    }

    // Các phương thức truy vấn đặc biệt khác có thể được thêm vào đây nếu cần
}

