package hms_backend.services;

import hms_backend.dto.RoomDto;
import hms_backend.dto.SignupRequest;
import hms_backend.dto.StockDto;
import hms_backend.dto.UserDto;
import hms_backend.entity.Room;
import hms_backend.entity.Stock;
import hms_backend.entity.User;
import hms_backend.entity.enums.UserRole;
import hms_backend.repository.RoomRepository;
import hms_backend.repository.StockRepository;
import hms_backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.aspectj.apache.bcel.Repository.getRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final StockRepository stockRepository;

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
    public RoomDto createRoom(RoomDto roomDto){
        if (roomRepository.findFirstByName(roomDto.getName()).isPresent()){
            throw new EntityExistsException("Room already exists with name "+roomDto.getName());
        }

        Room room=new Room();
        room.setName(roomDto.getName());
        room.setType(roomDto.getType());
        room.setPrice(roomDto.getPrice());
        Room createdRoom=roomRepository.save(room);
        return createdRoom.getRoomDto();
    }

    @Override
    public StockDto createStockItem(StockDto stockDto) {

        if (stockRepository.findFirstByName(stockDto.getName()).isPresent()){
            throw new EntityExistsException("Stock Item already exists with name"+stockDto.getName());
        }
        Stock stock=new Stock();
        stock.setName(stockDto.getName());
        stock.setQuantity(stockDto.getQuantity());
        Stock createdStockItem=stockRepository.save(stock);
        return createdStockItem.getStockDto();
    }

}
