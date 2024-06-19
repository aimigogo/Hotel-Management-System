package hms_backend.controller.RoomControllerTest;

import hms_backend.controller.RoomController;
import hms_backend.dto.RoomDto;
import hms_backend.services.RoomService;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateRoom_Success() {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(1L);
        when(roomService.createRoom(any(RoomDto.class))).thenReturn(roomDto);

        ResponseEntity<?> response = roomController.createRoom(roomDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roomDto, response.getBody());
    }

    @Test
    public void testCreateRoom_RoomExists() throws Exception {
        when(roomService.createRoom(any(RoomDto.class))).thenThrow(EntityExistsException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/createRoom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"roomId\":1,\"roomName\":\"Test Room\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("Room already exists"));
    }

    @Test
    public void testCreateRoom_BadRequest() throws Exception {
        when(roomService.createRoom(any(RoomDto.class))).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/createRoom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"roomId\":1,\"roomName\":\"Test Room\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Room not created, please try again"));
    }
}
