package hms_backend.transfer.resource;

import hms_backend.model.enums.Amenities;
import hms_backend.model.enums.Status;
import hms_backend.model.enums.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class RoomResource extends BaseResource{

    @NotNull
    private Status status;

    @NotNull
    private Amenities amenities;

    @NotNull
    private Type type;
}
