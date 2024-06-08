package hms_backend.mapper;


import hms_backend.model.Room;
import hms_backend.transfer.resource.RoomResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
public interface RoomMapper extends BaseMapper<Room, RoomResource> {

}
