package edu.ordermanager.infrastructure.adapter.in.rest.mapper;

import edu.ordermanager.domain.enums.Status;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request.UpdateOrderStatusRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper para actualizar el estado de una orden.
 */
@Component
public class OrderStatusMapper {

    /**
     * Actualiza el status de una orden desde el DTO.
     */
    public static void updateStatus(Order order, UpdateOrderStatusRequestDTO dto) {
        order.setStatus(Status.valueOf(dto.getStatus()));
    }
}
