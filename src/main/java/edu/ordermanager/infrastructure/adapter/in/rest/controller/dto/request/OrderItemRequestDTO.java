package edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequestDTO {

    @NotNull
    Long productId;

    @NotNull
    @Positive
    Integer quantity;
}
