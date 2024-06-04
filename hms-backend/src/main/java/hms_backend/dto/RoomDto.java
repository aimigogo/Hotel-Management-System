package hms_backend.dto;

import hms_backend.model.enums.Amenities;
import hms_backend.model.enums.Status;
import hms_backend.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private Long id;

    private Status status;

    private Amenities amenities;

    private Type type;
}
