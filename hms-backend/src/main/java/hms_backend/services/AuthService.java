package hms_backend.services;

import hms_backend.dto.SignupRequest;
import hms_backend.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
