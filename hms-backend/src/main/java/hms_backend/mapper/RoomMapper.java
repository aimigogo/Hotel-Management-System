package hms_backend.mapper;

import hms_backend.dto.RoomDto;
import hms_backend.model.Room;

public interface RoomMapper {

    public static RoomDto mapToRoomDto(Room room){
        return new RoomDto(
                room.getId(),
                room.getStatus(),
                room.getAmenities(),
                room.getType()
        );
    }

    public static Room mapToRoom(RoomDto roomDto){
        return new Room(
                roomDto.getId(),
                roomDto.getStatus(),
                roomDto.getAmenities(),
                roomDto.getType()
        );
    }
}
