package edu.ordermanager.domain.model;

import edu.ordermanager.domain.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa una orden que agrupa productos solicitados por un cliente.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    Long id;
    Long customerId;
    Status status = Status.CREATED;
    List<OrderItem> items;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    /**
     * Calcula y retorna el monto total de la orden.
     * Si no hay ítems, retorna BigDecimal.ZERO.
     *
     * @return Monto total sumando los subtotales de los ítems.
     */
    public BigDecimal getTotalAmount() {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
