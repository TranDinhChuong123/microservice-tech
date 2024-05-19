package com.programingtech.courseservice.services;


import com.programingtech.courseservice.models.Prerequisite;
import com.programingtech.courseservice.repositories.PrerequisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrerequisiteService {

    @Autowired
    private PrerequisiteRepository prerequisiteRepository;

    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteRepository.findAll();
    }

    public Prerequisite savePrerequisite(Prerequisite prerequisite) {
        return prerequisiteRepository.save(prerequisite);
    }

    public void deletePrerequisite(Long prerequisiteId) {
        prerequisiteRepository.deleteById(prerequisiteId);
    }

    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần
}
