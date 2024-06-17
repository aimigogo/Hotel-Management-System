package hms_backend.dto;

import hms_backend.entity.enums.Available;
import hms_backend.entity.enums.Type;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;

    private String name;

    private Type type;

    private BigDecimal price;

    private Available available;
}
