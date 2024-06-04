package hms_backend.dto;

import hms_backend.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String firstname;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Role role;
}
