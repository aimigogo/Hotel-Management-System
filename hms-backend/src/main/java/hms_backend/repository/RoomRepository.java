package hms_backend.repository;

import hms_backend.model.Room;
import hms_backend.model.enums.Amenities;
import hms_backend.model.enums.Status;
import hms_backend.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface RoomRepository extends JpaRepository<Room,Long> {
    Room getRoomByStatus(Status status);

    Room getRoomByAmenities(Amenities amenities);

    Room getRoomByType(Type type);
}
