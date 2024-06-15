package hms_backend.services.admin;

import hms_backend.dto.RoomDto;
import hms_backend.entity.Room;
import hms_backend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;

    public boolean addRoom(RoomDto roomDto){
        try{
            Room room=new Room();

            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setType(room.getType());
            room.setAvailable(true);

            roomRepository.save(room);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<Object> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Object convertToDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setType(room.getType());
        roomDto.setPrice(room.getPrice());
        return roomDto;
    }
}


