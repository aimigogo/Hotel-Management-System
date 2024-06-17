package hms_backend.controller;

import hms_backend.dto.RoomDto;
import hms_backend.services.RoomService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    //Create Room
    @PostMapping("/createRoom")
    public ResponseEntity<?> createRoom(@RequestBody RoomDto roomDto){
        try{
            RoomDto createdRoom=roomService.createRoom(roomDto);
            return new ResponseEntity<>(createdRoom, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("Room already exists",HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("Room not created,please try again",HttpStatus.BAD_REQUEST);
        }
    }

    //Update Room
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        try {
            RoomDto updatedRoom = roomService.updateRoom(id, roomDto);
            return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Room update failed, please try again", HttpStatus.BAD_REQUEST);
        }
    }
    //Delete Room
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    //Get List of all Rooms
    @GetMapping("/rooms")
    public ResponseEntity<List<Object>> getAllRooms(){
        List<Object> rooms=roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
}
