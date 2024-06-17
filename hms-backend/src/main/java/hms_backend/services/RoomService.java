package hms_backend.services;

import hms_backend.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto createRoom(RoomDto roomDto);
    List<Object> getAllRooms();

    RoomDto updateRoom(Long id, RoomDto roomDto);
    void deleteRoom(Long id);
}
