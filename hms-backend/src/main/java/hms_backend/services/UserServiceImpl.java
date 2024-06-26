package hms_backend.services;

import hms_backend.dto.UserDto;
import hms_backend.entity.User;
import hms_backend.entity.enums.UserRole;
import hms_backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findFirstByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public List<UserDto> getAllEmployees() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> user.getUserRole() != UserRole.ADMIN)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setSection(user.getSection());
        userDto.setShift(user.getShift());
        return userDto;
    }

    public UserDto updateUser(Long id,UserDto userDto){
        User user=userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setSection(userDto.getSection());
        user.setShift(userDto.getShift());
        userRepository.save(user);
        return new UserDto();

    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }




}
