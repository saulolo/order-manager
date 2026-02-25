package edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

@Builder
@JsonPropertyOrder({"productId", "quantity", "productName"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderItemResponseDTO(
        Long productId,
        Integer quantity,
        String productName
) {
}
