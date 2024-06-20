package hms_backend.entity;

import hms_backend.dto.RoomDto;
import hms_backend.dto.StockDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "stocks")
    private List<Room> rooms;

    public StockDto getStockDto(){
        StockDto dto=new StockDto();
        dto.setId(id);
        dto.setName(name);
        dto.setQuantity(quantity);
        dto.setUserId(user.getId());
        return dto;
    }
}
