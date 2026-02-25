package edu.ordermanager.application.service;

import edu.ordermanager.domain.enums.Status;
import edu.ordermanager.domain.exception.OrderNotFoundException;
import edu.ordermanager.domain.exception.OrderStatusException;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.port.in.UpdateOrderStatusUseCase;
import edu.ordermanager.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static edu.ordermanager.common.constants.Constants.INVALID_ORDER_STATUS;
import static edu.ordermanager.common.constants.Constants.ORDER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
public class UpdateOrderStatusService implements UpdateOrderStatusUseCase {


    private final OrderRepository orderRepository;

    /**
     * Confirma una orden por su ID.
     *
     * @param orderId ID de la orden a confirmar.
     */
    @Override
    public void confirmOrder(Long orderId) {
        log.info("Confirmando orden, ID {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.warn("Orden no encontrada, ID {}", orderId);
                    return new OrderNotFoundException(String.format(ORDER_NOT_FOUND, orderId));
                });

        if (order.getStatus() != Status.CREATED) {
            log.warn("No se puede confirmar orden en estado {}", order.getStatus());
            throw new OrderStatusException(INVALID_ORDER_STATUS);
        }

        order.setStatus(Status.CONFIRMED);
        orderRepository.update(order);
        log.info("Orden confirmada, ID {}", orderId);
    }

    /**
     * Cancela una orden por su ID.
     *
     * @param orderId ID de la orden a cancelar.
     */
    @Override
    public void cancelOrder(Long orderId) {
        log.info("Cancelando orden, ID {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.warn("Orden no encontrada, ID {}", orderId);
                    return new OrderNotFoundException(String.format(ORDER_NOT_FOUND, orderId));
                });

        if (order.getStatus() != Status.CREATED) {
            log.warn("No se puede cancelar orden en estado {}", order.getStatus());
            throw new OrderStatusException(INVALID_ORDER_STATUS);
        }

        order.setStatus(Status.CANCELLED);
        orderRepository.update(order);
        log.info("Orden cancelada, ID {}", orderId);
    }
}