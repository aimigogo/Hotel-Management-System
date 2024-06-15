package hms_backend.controller.admin;

import hms_backend.dto.RoomDto;
import hms_backend.services.admin.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

//    @PostMapping("/room")
//    public ResponseEntity<?> addRoom(@RequestBody RoomDto roomDto){
//        boolean success= roomService.addRoom(roomDto);
//        if (success){
//            return ResponseEntity.status(HttpStatus.OK).build();
//        }else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
}
