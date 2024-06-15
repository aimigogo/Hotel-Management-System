package hms_backend.entity;

import hms_backend.dto.RoomDto;
import hms_backend.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private BigDecimal price;

    private boolean available;

    public RoomDto getRoomDto(){
        RoomDto dto=new RoomDto();
        dto.setId(id);
        dto.setName(name);
        dto.setType(type);
        dto.setPrice(price);
        return dto;
    }
}
