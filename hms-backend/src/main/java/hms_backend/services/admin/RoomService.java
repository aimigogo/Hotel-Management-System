package hms_backend.services.admin;

import hms_backend.dto.RoomDto;

import java.util.List;

public interface RoomService {
    boolean addRoom(RoomDto roomDto);
    List<Object> getAllRooms();
}
