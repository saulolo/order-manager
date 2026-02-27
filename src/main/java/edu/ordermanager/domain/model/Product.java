package edu.ordermanager.domain.model;

import edu.ordermanager.domain.vo.Description;
import edu.ordermanager.domain.vo.Price;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Representa un producto disponible en el sistema.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    Long id;
    String name;
    Description description;
    Price price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
