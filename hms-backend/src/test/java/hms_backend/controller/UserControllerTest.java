package hms_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hms_backend.service.UserService;
import hms_backend.transfer.LoginResponse;
import hms_backend.transfer.resource.SignupRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testLoginSuccess() throws Exception {
        // Arrange
        SignupRequest loginResource = new SignupRequest();
        loginResource.setUsername("aimigogo");
        loginResource.setPassword("test1234");

        // Mock the UserService response
        Mockito.when(userService.login(Mockito.any(SignupRequest.class))).thenReturn(new LoginResponse("Login Success",true));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("aimigogo"))
                .andExpect(jsonPath("$.data.password").value("test1234"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        // Arrange
        SignupRequest loginResource = new SignupRequest();
        loginResource.setUsername("wrongUser");
        loginResource.setPassword("wrongPassword");

        // Mock the UserService response to return null for failed login
        Mockito.when(userService.login(Mockito.any(SignupRequest.class))).thenReturn(null);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginResource)))
                .andExpect(status().isUnauthorized());
    }
    
    
    
    
    
    
    
//    @BeforeEach
//    void setUp(){
//        UserResource userResource=new UserResource();
//        userResource.setUserId(1L);
//        userResource.setFirstname("aimilia");
//
//        given(userService.getUserById(1L)).willReturn(userResource);
//
//    }
//
//    @Test
//    public void testGetUserById() throws Exception {
//        mockMvc.perform(get("/api/users/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$.userId").value(1))
//                        .andExpect(jsonPath("$.firstname").value("aimilia"));
//    }
}