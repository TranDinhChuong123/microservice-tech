package com.programingtech.courseservice.services;

import org.springframework.stereotype.Service;
import com.programingtech.courseservice.models.Room;
import com.programingtech.courseservice.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    // Các phương thức truy vấn đặc biệt khác có thể được thêm vào đây nếu cần
}
