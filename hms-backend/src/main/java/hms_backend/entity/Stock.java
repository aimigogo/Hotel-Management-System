package hms_backend.entity;

import hms_backend.dto.RoomDto;
import hms_backend.dto.StockDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public StockDto getStockDto(){
        StockDto dto=new StockDto();
        dto.setId(id);
        dto.setName(name);
        dto.setQuantity(quantity);
        return dto;
    }
}
