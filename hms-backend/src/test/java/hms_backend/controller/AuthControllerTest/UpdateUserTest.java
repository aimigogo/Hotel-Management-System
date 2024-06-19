package hms_backend.controller.AuthControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import hms_backend.controller.AuthController;
import hms_backend.dto.UserDto;
import hms_backend.services.AuthService;
import hms_backend.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static hms_backend.entity.enums.Section.RECEPTION;
import static hms_backend.entity.enums.Shift.MORNING;
import static hms_backend.entity.enums.UserRole.EMPLOYEE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class UpdateUserTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;
    @Mock
    private AuthService authService;
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = mock(AuthService.class);
    }
    @Test
    public void testUpdateUser_Success() throws Exception {
        Long userId = 1L;
        UserDto updatedUserDto = new UserDto(userId, "John Doe", "john.doe@example.com",EMPLOYEE,RECEPTION,MORNING);

        when(userService.updateUser(eq(userId), any(UserDto.class))).thenReturn(updatedUserDto);

        String requestBody = objectMapper.writeValueAsString(updatedUserDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(userService, times(1)).updateUser(eq(userId), any(UserDto.class));
    }

    @Test
    public void testUpdateUser_UserNotFound() throws Exception {
        Long userId = 1L;
        UserDto updatedUserDto = new UserDto(userId, "John Doe", "john.doe@example.com",EMPLOYEE,RECEPTION,MORNING);

        when(userService.updateUser(eq(userId), any(UserDto.class))).thenThrow(new EntityNotFoundException());

        String requestBody = objectMapper.writeValueAsString(updatedUserDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(userService, times(1)).updateUser(eq(userId), any(UserDto.class));
    }

    @Test
    public void testUpdateUser_UpdateFailed() throws Exception {
        Long userId = 1L;
        UserDto updatedUserDto = new UserDto(userId, "John Doe", "john.doe@example.com",EMPLOYEE,RECEPTION,MORNING);

        when(userService.updateUser(eq(userId), any(UserDto.class))).thenThrow(new RuntimeException());

        String requestBody = objectMapper.writeValueAsString(updatedUserDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User update failed, please try again"));

        verify(userService, times(1)).updateUser(eq(userId), any(UserDto.class));
    }
}