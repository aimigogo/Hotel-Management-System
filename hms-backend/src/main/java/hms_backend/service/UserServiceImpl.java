package hms_backend.service;

import hms_backend.dto.UserDto;
import hms_backend.mapper.UserMapper;
import hms_backend.model.User;
import hms_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user= UserMapper.mapToUser(userDto);
        User savedUser= userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }



}
