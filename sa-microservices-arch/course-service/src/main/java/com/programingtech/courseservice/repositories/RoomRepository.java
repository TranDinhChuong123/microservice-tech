package com.programingtech.courseservice.repositories;


import com.programingtech.courseservice.models.Class;
import com.programingtech.courseservice.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // Các phương thức truy vấn đặc biệt có thể được định nghĩa ở đây nếu cần

}

