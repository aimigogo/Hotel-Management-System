package hms_backend.controller.AuthControllerTest;

import hms_backend.controller.AuthController;
import hms_backend.dto.SignupRequest;
import hms_backend.dto.UserDto;
import hms_backend.services.AuthService;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class SignUpUserTest {


    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignupUser_Success() {
        SignupRequest signupRequest = new SignupRequest();

        UserDto userDto = new UserDto();

        when(authService.createUser(any(SignupRequest.class))).thenReturn(userDto);

        ResponseEntity<?> response = authController.signupUser(signupRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testSignupUser_EntityExistsException() {
        SignupRequest signupRequest = new SignupRequest();

        doThrow(new EntityExistsException()).when(authService).createUser(any(SignupRequest.class));

        ResponseEntity<?> response = authController.signupUser(signupRequest);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertEquals("User already exists", response.getBody());
    }

    @Test
    public void testSignupUser_OtherException() {
        SignupRequest signupRequest = new SignupRequest();

        doThrow(new RuntimeException()).when(authService).createUser(any(SignupRequest.class));

        ResponseEntity<?> response = authController.signupUser(signupRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not created,please try again", response.getBody());
    }
}