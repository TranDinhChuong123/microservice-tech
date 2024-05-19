package com.programingtech.courseservice.services;


import com.programingtech.courseservice.models.Major;
import com.programingtech.courseservice.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService {

    private final MajorRepository majorRepository;

    @Autowired
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    public Optional<Major> getMajorById(Long id) {
        return majorRepository.findById(id);
    }

    public Major saveMajor(Major major) {
        return majorRepository.save(major);
    }

    public void deleteMajorById(Long id) {
        majorRepository.deleteById(id);
    }

    // Add other methods as needed
}

