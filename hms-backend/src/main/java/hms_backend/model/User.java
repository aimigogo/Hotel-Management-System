package hms_backend.model;

import hms_backend.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users",indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator",sequenceName = "USER_SEQ", initialValue = 1,allocationSize = 1)

public class User{
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @Column(updatable = false)
    private Long id;
    @Column
    private String name;
    @NotNull(message = "Email address cannot be null")
    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length =10,nullable = false )
    private Role role;



}
