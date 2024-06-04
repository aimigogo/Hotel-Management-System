package hms_backend.model;

import hms_backend.model.enums.Amenities;
import hms_backend.model.enums.Status;
import hms_backend.model.enums.Type;
import jakarta.persistence.*;
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
@Table(name="rooms",indexes = {@Index(columnList = "id")})
@SequenceGenerator(name = "idGenerator",sequenceName = "ROOM_SEQ",initialValue = 1,allocationSize = 1)
public class Room{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @Column(updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length=10,nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private Amenities amenities;

    @Enumerated(EnumType.STRING)
    @Column(length = 8,nullable = false)
    private Type type;
}
