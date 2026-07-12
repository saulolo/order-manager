package edu.ordermanager.domain.model;

import edu.ordermanager.domain.enums.Status;
import edu.ordermanager.domain.exception.OrderDomainException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static edu.ordermanager.common.constants.Constants.*;

/**
 * Representa una orden que agrupa productos solicitados por un cliente.
 */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    Long id;
    Long customerId;
    String customerEmail;
    Status status;
    List<OrderItem> items;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;


    /**
     * Garantiza que la orden tenga al menos un producto.
     * @param customerId    Id del cliente.
     * @param customerEmail Email del cliente para notificaciones.
     * @param items         Lista de ítems (productos), debe tener al menos uno.
     * @return Order válida.
     */
    public static Order create(Long customerId, String customerEmail, List<OrderItem> items) {
        if (items == null || items.isEmpty()) throw new OrderDomainException(ORDER_MINIMUM_PRODUCT_REQUIRED);

        return Order.builder()
                .customerId(customerId)
                .customerEmail(customerEmail)
                .items(items)
                .status(Status.CREATED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Cambia el estado de la orden a CONFIRMED si está en CREATED.
     * Lanza excepción si no cumple la regla.
     */
    public void confirm() {
        if (status != Status.CREATED) {
            throw new OrderDomainException(ORDER_CONFIRMATION_STATE_ERROR);
        }
        this.status = Status.CONFIRMED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Cambia el estado de la orden a CANCELLED si está en CREATED.
     * Lanza excepción si no cumple la regla.
     */
    public void cancel() {
        if (status != Status.CREATED) {
            throw new OrderDomainException(ORDER_CANCELLATION_STATE_ERROR);
        }
        this.status = Status.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }


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
