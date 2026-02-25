package edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@JsonPropertyOrder({"id", "customerId", "items", "status", "createdAt", "updatedAt"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderResponseDTO(
        Long id,
        Long customerId,
        List<OrderItemResponseDTO> items,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
