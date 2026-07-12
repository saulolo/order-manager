package edu.ordermanager.infrastructure.adapter.in.rest.mapper;

import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request.OrderItemRequestDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request.OrderRequestDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.response.OrderItemResponseDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.response.OrderResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para transformar entre Order y sus DTOs.
 */
@Component
public class OrderMapper {


    // ========== ENTITY → RESPONSE DTO ==========

    /**
     * Convierte un Order (modelo de dominio) a OrderResponseDTO.
     */
    public static OrderResponseDTO toResponseDTO(Order order, List<OrderItemResponseDTO> itemDTOs) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .items(itemDTOs)
                .status(order.getStatus().name())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    /**
     * Convierte una lista de OrderItem (modelo de dominio) a lista de OrderItemResponseDTO.
     * Puedes incluir el nombre del producto según tu lógica (por ejemplo, consultando el repositorio).
     */
    public static List<OrderItemResponseDTO> toItemResponseDTOList(List<OrderItem> items) {
        return items.stream()
                .map(item -> OrderItemResponseDTO.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }


    // ========== REQUEST DTO → ENTITY ==========

    /**
     * Convierte un OrderRequestDTO a Order (modelo de dominio).
     */
    public static Order toDomain(OrderRequestDTO dto, String customerEmail) {
        List<OrderItem> items = dto.getItems().stream()
                .map(OrderMapper::toDomainOrderItem)
                .collect(Collectors.toList());

        return Order.create(dto.getCustomerId(), customerEmail ,items);
    }

    /**
     * Convierte un OrderItemRequestDTO a OrderItem (modelo de dominio).
     */
    public static OrderItem toDomainOrderItem(OrderItemRequestDTO dto) {
        return OrderItem.builder()
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .build();
    }
}
