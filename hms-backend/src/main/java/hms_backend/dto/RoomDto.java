package hms_backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;

    private String name;

    private String type;

    private BigDecimal price;

    private boolean available;
}
