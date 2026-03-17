package edu.ordermanager.infrastructure.adapter.out.persistence.mongo.document;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDocument {

    Long productId;
    Integer quantity;
    BigDecimal unitPrice;
    BigDecimal subtotal;
}
