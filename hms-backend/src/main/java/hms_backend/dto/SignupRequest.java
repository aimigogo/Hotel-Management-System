package hms_backend.dto;

import hms_backend.entity.enums.Section;
import hms_backend.entity.enums.Shift;
import hms_backend.entity.enums.UserRole;
import lombok.Data;

@Data
public class SignupRequest {
    private String email;

    private String password;

    private String name;

    private UserRole userRole;

    private Section section;

    private Shift shift;
}
