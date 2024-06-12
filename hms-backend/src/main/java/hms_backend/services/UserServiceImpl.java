package hms_backend.services;

import hms_backend.dto.UserDto;
import hms_backend.entity.User;
import hms_backend.repository.UserRepository;
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
        return null;
    }

//    public List<UserDto> getAllEmployees(){
//        List<User> users=userRepository.findAll();
//        return users.stream().map((user -> UserMapper.mapToUserDto(user))
//                .collect(Collectors.toList()));
//    }
}
