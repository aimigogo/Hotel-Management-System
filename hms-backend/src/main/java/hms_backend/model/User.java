package hms_backend.model;

import hms_backend.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users",indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator",sequenceName = "USER_SEQ", initialValue = 1,allocationSize = 1)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @Column(updatable = false)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column
    @NotNull
    private String username;
    @Column
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length =10,nullable = false )
    private Role role;



}
