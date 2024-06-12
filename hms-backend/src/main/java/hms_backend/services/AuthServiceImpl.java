package hms_backend.services;

import hms_backend.dto.SignupRequest;
import hms_backend.dto.UserDto;
import hms_backend.entity.User;
import hms_backend.entity.enums.UserRole;
import hms_backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        Optional<User> adminAccount=userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount.isEmpty()){
            User user=new User();
            user.setEmail("admin@gmail.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin created successfully");
        }else{
            System.out.println("Admin Account already exists");
        }
    }

    public UserDto createUser(SignupRequest signupRequest){
        if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User already exists with email "+signupRequest.getEmail());
        }

        User user=new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.EMPLOYEE);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser=userRepository.save(user);
        return createdUser.getUserDto();
    }
}
