package edu.ordermanager.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa un producto disponible en el sistema.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    Long id;
    String name;
    String description;
    BigDecimal price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
