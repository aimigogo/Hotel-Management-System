package hms_backend.service;


import hms_backend.model.Room;
import hms_backend.model.enums.Amenities;
import hms_backend.model.enums.Status;
import hms_backend.model.enums.Type;

public interface RoomService extends BaseService<Room,Long>{
    Room getRoomByStatus(Status status);

    Room getRoomByAmenities(Amenities amenities);

    Room getRoomByType(Type type);

}
