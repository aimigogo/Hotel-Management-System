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
public class Stock{
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @Column(updatable = false)
    private Long id;

    @Column
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;
}
