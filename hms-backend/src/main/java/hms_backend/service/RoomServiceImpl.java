package hms_backend.service;

import hms_backend.dto.RoomDto;
import hms_backend.mapper.RoomMapper;
import hms_backend.model.Room;
import hms_backend.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService{

    private RoomRepository roomRepository;

    @Override
    public RoomDto createRoom(RoomDto roomDto) {

        Room room= RoomMapper.mapToRoom(roomDto);
        Room savedRoom=roomRepository.save(room);
        return RoomMapper.mapToRoomDto(savedRoom);
    }
}
