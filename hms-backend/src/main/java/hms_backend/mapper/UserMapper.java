package hms_backend.mapper;

import hms_backend.dto.UserDto;
import hms_backend.model.User;

public interface UserMapper {

    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstname(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole()
        );
    }


}
