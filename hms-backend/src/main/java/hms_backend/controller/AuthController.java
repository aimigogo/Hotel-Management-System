package hms_backend.controller;

import hms_backend.dto.AuthenticationRequest;
import hms_backend.dto.AuthenticationResponse;
import hms_backend.dto.SignupRequest;
import hms_backend.dto.UserDto;
import hms_backend.entity.User;
import hms_backend.repository.UserRepository;
import hms_backend.services.AuthService;
import hms_backend.services.UserService;
import hms_backend.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
}
