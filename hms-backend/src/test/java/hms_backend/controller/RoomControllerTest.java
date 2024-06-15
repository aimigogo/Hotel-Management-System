package hms_backend.controller;

import hms_backend.controller.admin.RoomController;
import hms_backend.dto.RoomDto;
import hms_backend.services.admin.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class RoomControllerTest {
    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }
    @Test
    public void testAddRoomSuccess() throws Exception {
        // Mocking the service method to return true (success)
        when(roomService.addRoom(any(RoomDto.class))).thenReturn(true);

        // Create a request with a valid RoomDto
        String requestBody = "{\"roomId\": 1, \"roomName\": \"Test Room\"}";

        // Perform POST request
        RequestBuilder requestBuilder = post("/api/room/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // Verify the response status
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testAddRoomFailure() throws Exception {
        // Mocking the service method to return false (failure)
        when(roomService.addRoom(any(RoomDto.class))).thenReturn(false);

        // Create a request with a valid RoomDto
        String requestBody = "{\"roomId\": 1, \"roomName\": \"Test Room\"}";

        // Perform POST request
        RequestBuilder requestBuilder = post("/api/room/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // Verify the response status
        resultActions.andExpect(status().isBadRequest());
    }
}
