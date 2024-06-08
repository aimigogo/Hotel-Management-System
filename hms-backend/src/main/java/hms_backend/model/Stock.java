package hms_backend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name="stock",indexes = {@Index(columnList = "name")})
@SequenceGenerator(name = "idGenerator",sequenceName = "STOCK_SEQ", initialValue = 1,allocationSize = 1)
public class Stock extends BaseModel{

    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;
}
