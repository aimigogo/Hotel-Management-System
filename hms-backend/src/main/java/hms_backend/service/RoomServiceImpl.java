package hms_backend.service;


import hms_backend.mapper.RoomMapper;
import hms_backend.model.Room;
import hms_backend.model.enums.Amenities;
import hms_backend.model.enums.Status;
import hms_backend.model.enums.Type;
import hms_backend.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomServiceImpl extends BaseServiceImpl<Room> implements RoomService{

    private RoomRepository roomRepository;


    @Override
    protected JpaRepository<Room, Long> getRepository() {
        return roomRepository;
    }

    @Override
    public Room getRoomByStatus(Status status) {
        return roomRepository.getRoomByStatus(status);
    }

    @Override
    public Room getRoomByAmenities(Amenities amenities) {
        return roomRepository.getRoomByAmenities(amenities);
    }

    @Override
    public Room getRoomByType(Type type) {
        return roomRepository.getRoomByType(type);
    }
}
