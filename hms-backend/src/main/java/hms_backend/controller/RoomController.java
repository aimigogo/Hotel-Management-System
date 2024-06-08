package hms_backend.controller;


import hms_backend.mapper.BaseMapper;
import hms_backend.mapper.RoomMapper;
import hms_backend.model.Room;
import hms_backend.model.enums.Amenities;
import hms_backend.model.enums.Status;
import hms_backend.model.enums.Type;
import hms_backend.service.BaseService;
import hms_backend.service.RoomService;
import hms_backend.transfer.ApiResponse;
import hms_backend.transfer.resource.RoomResource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController extends BaseController<Room, RoomResource> {

    private RoomService roomService;
    private final RoomMapper roomMapper;


    @Override
    protected BaseService<Room, Long> getBaseService() {
        return null;
    }

    @Override
    protected BaseMapper<Room, RoomResource> getMapper() {
        return null;
    }

    @GetMapping("{status}")
    public ResponseEntity<ApiResponse<RoomResource>> getRoomByStatus(@PathVariable("status")final Status status){
        return ResponseEntity.ok(
                ApiResponse.<RoomResource>builder()
                        .data(roomMapper.toResource(roomService.getRoomByStatus(status)))
                        .build());
    }

    @GetMapping("/amenities/{amenities}")
    public ResponseEntity<ApiResponse<RoomResource>> getRoomByAmenities(@PathVariable("amenities")final Amenities amenities){
        return ResponseEntity.ok(
                ApiResponse.<RoomResource>builder()
                        .data(roomMapper.toResource(roomService.getRoomByAmenities(amenities)))
                        .build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<RoomResource>> getRoomByType(@PathVariable("type")final Type type){
        final RoomResource roomResource= getMapper().toResource(roomService.getRoomByType(type));
        return ResponseEntity.ok(
                ApiResponse.<RoomResource>builder().data(roomResource).build());
    }
}


