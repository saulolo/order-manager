package edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDTO {

    @NotNull
    Long customerId;

    @NotNull @Size(min = 1, message = "La orden debe tener al menos un ítem")
    List<OrderItemRequestDTO> items;
}
