package hms_backend.transfer.resource;

import hms_backend.model.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserResource extends  BaseResource{

    private String firstname;

    private String lastname;

    @NotNull(message = "Email cannot be null")
    private String email;

    private String username;

    @NotNull
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
