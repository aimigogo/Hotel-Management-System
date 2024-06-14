package hms_backend.dto;

import hms_backend.entity.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}
