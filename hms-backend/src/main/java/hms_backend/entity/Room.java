package hms_backend.entity;

import hms_backend.dto.RoomDto;
import hms_backend.entity.enums.Available;
import hms_backend.entity.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private Type type;

    private BigDecimal price;


    @Enumerated(EnumType.STRING)
    private Available available;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToMany
//    @JoinTable(
//            name = "room_stock",
//            joinColumns = @JoinColumn(name = "room_id"),
//            inverseJoinColumns = @JoinColumn(name = "stock_id")
//    )
//    private List<Stock> stocks;

    public RoomDto getRoomDto(){
        RoomDto dto=new RoomDto();
        dto.setId(id);
        dto.setName(name);
        dto.setType(type);
        dto.setPrice(price);
        dto.setAvailable(available);
//        dto.setUserId(user.getId());
        return dto;
    }
}
