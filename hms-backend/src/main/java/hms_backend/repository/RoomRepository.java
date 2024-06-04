package hms_backend.repository;

import hms_backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface RoomRepository extends JpaRepository<Room,Long> {
}
