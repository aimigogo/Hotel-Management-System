package hms_backend.services;

import hms_backend.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<UserDto> getAllEmployees();

    UserDto updateUser(Long id,UserDto userDto);

    void deleteUser(Long id);
}
