package hms_backend.services;

import hms_backend.dto.*;
import hms_backend.entity.enums.Section;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
