package hms_backend.services;

import hms_backend.dto.RoomDto;
import hms_backend.dto.UserDto;
import hms_backend.entity.Room;
import hms_backend.entity.User;
import hms_backend.repository.RoomRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;


    public RoomDto createRoom(RoomDto roomDto){
        if (roomRepository.findFirstByName(roomDto.getName()).isPresent()){
            throw new EntityExistsException("Room already exists with name "+roomDto.getName());
        }

        Room room=new Room();
        room.setName(roomDto.getName());
        room.setType(roomDto.getType());
        room.setPrice(roomDto.getPrice());
        room.setAvailable(roomDto.getAvailable());
        Room createdRoom=roomRepository.save(room);
        return createdRoom.getRoomDto();
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
        roomDto.setAvailable(room.getAvailable());
        return roomDto;
    }

    public RoomDto updateRoom(Long id, RoomDto roomDto){
        Room room=roomRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Room not found"));
        room.setName(roomDto.getName());
        room.setType(roomDto.getType());
        room.setPrice(roomDto.getPrice());
        room.setAvailable(roomDto.getAvailable());
        roomRepository.save(room);
        return new RoomDto();

    }

    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }
}


