package hms_backend.controller;

import hms_backend.dto.*;
import hms_backend.entity.User;
import hms_backend.repository.UserRepository;
import hms_backend.services.AuthService;
import hms_backend.services.StockService;
import hms_backend.services.UserService;
import hms_backend.services.admin.RoomService;
import hms_backend.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    private final RoomService roomService;

    private final StockService stockService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        try{
            UserDto createdUser=authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("User already exists",HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("User not created,please try again",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/createRoom")
    public ResponseEntity<?> createRoom(@RequestBody RoomDto roomDto){
        try{
            RoomDto createdRoom=authService.createRoom(roomDto);
            return new ResponseEntity<>(createdRoom, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("Room already exists",HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("Room not created,please try again",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/createStockItem")
    public ResponseEntity<?> createdStockItem(@RequestBody StockDto stockDto){
        try{
            StockDto createdStock=authService.createStockItem(stockDto);
            return new ResponseEntity<>(createdStock, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("Stock item already exists",HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("Stock item not created,please try again",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails=userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt=jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        if (optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return authenticationResponse;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<UserDto>> getAllEmployees(){
        List<UserDto> employees=userService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/rooms")
    public ResponseEntity<List<Object>> getAllRooms(){
        List<Object> rooms=roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Object>> getAllStockItems(){
        List<Object> stockItems=stockService.getAllStockItems();
        return ResponseEntity.ok(stockItems);
    }

    @PutMapping("/employees/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            UserDto updatedUser = userService.updateUser(id, userDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("User update failed, please try again", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    }

