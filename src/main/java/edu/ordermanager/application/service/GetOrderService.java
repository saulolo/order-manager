package edu.ordermanager.application.service;

import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.port.in.GetOrderUseCase;
import edu.ordermanager.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class GetOrderService implements GetOrderUseCase {

    private final OrderRepository orderRepository;

    /**
     * Busca una orden por su identificador.
     *
     * @param orderId ID de la orden.
     * @return Orden encontrada, o vacía si no existe.
     */
    @Override
    public Optional<Order> getOrderById(Long orderId) {
        log.info("Buscando orden con ID {}", orderId);
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            log.info("Orden encontrada, ID {}", orderId);
        } else {
            log.warn("Orden no encontrada, ID {}", orderId);
        }
        return order;
    }
}