package edu.ordermanager.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representa un ítem de una orden, asociado a un producto y cantidad.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {

    Long productId;
    int quantity;
    BigDecimal unitPrice;
    BigDecimal subtotal;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    /**
     * Calcula y retorna el subtotal del ítem.
     * Si el precio unitario es nulo, retorna BigDecimal.ZERO.
     *
     * @return Subtotal (unitPrice * quantity) del ítem.
     */
    public BigDecimal getSubtotal() {
        if (unitPrice == null) {
            return BigDecimal.ZERO;
        }
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
