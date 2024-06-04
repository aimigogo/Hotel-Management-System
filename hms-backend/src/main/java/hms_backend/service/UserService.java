package hms_backend.service;

import hms_backend.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
