package hms_backend.transfer.resource;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class StockResource extends BaseResource{

    @NotNull
    private String name;

    @NotNull
    private Integer quantity;
}
