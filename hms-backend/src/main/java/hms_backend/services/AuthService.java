package hms_backend.services;

import hms_backend.dto.RoomDto;
import hms_backend.dto.SignupRequest;
import hms_backend.dto.StockDto;
import hms_backend.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    RoomDto createRoom(RoomDto roomDto);
    StockDto createStockItem(StockDto stockDto);

}
