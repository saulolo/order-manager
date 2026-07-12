package edu.ordermanager.domain.port.in;

import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request.OrderRequestDTO;

import java.util.List;

public interface CreateOrderUseCase {

    /**
     * Crea una orden para un cliente con la lista de ítems indicada.
     *
     * @param customerId ID del cliente.
     * @param items Lista de productos a agregar a la orden.
     * @return La orden creada.
     */
    Order createOrder(OrderRequestDTO dto);

}
