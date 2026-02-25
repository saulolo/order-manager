package edu.ordermanager.domain.port.in;

import edu.ordermanager.domain.model.Order;

import java.util.Optional;

public interface GetOrderUseCase {

    /**
     * Busca una orden por su identificador.
     *
     * @param orderId ID de la orden.
     * @return Orden encontrada, o vacía si no existe.
     */
    Optional<Order> getOrderById(Long orderId);

}
