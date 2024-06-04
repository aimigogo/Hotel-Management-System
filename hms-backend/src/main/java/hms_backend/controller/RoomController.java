package hms_backend.controller;

import hms_backend.dto.RoomDto;
import hms_backend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto){
        RoomDto savedRoom=roomService.createRoom(roomDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

}
