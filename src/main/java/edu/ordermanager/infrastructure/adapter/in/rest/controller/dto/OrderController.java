package edu.ordermanager.infrastructure.adapter.in.rest.controller.dto;

import edu.ordermanager.application.service.CreateOrderService;
import edu.ordermanager.application.service.GetOrderService;
import edu.ordermanager.application.service.UpdateOrderStatusService;
import edu.ordermanager.domain.enums.Status;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request.OrderRequestDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request.UpdateOrderStatusRequestDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.response.ApiResponseDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.response.OrderItemResponseDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.response.OrderResponseDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.mapper.OrderMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static edu.ordermanager.common.constants.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderService createOrderService;
    private final GetOrderService getOrderService;
    private final UpdateOrderStatusService updateOrderStatusService;


    /**
     * Crea una nueva orden.
     * @param orderRequestDTO datos de la orden a crear
     * @return ApiResponseDTO con la orden creada y mensaje de éxito
     */
    @PostMapping
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        Order createdOrder = createOrderService.createOrder(orderRequestDTO);

        List<OrderItemResponseDTO> itemDTOs = OrderMapper.toItemResponseDTOList(createdOrder.getItems());
        OrderResponseDTO responseDTO = OrderMapper.toResponseDTO(createdOrder, itemDTOs);

        ApiResponseDTO<OrderResponseDTO> apiResponse = ApiResponseDTO.success(
                responseDTO,
                ORDER_CREATED
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    /**
     * Obtiene una orden por su identificador.
     * @param id      identificador de la orden
     * @param request objeto HttpServletRequest para extraer el path usado
     * @return ApiResponseDTO con la orden encontrada o error si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> getOrderById(@PathVariable Long id,
                                                                         HttpServletRequest request) {
        Optional<Order> optionalOrder = getOrderService.getOrderById(id);

        if (optionalOrder.isEmpty()) {
            ApiResponseDTO<OrderResponseDTO> apiResponse = ApiResponseDTO.error(
                    "Orden no encontrada",
                    ORDER_NOT_FOUND,
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        Order order = optionalOrder.get();
        List<OrderItemResponseDTO> itemDTOs = OrderMapper.toItemResponseDTOList(order.getItems());
        OrderResponseDTO responseDTO = OrderMapper.toResponseDTO(order, itemDTOs);

        ApiResponseDTO<OrderResponseDTO> apiResponse = ApiResponseDTO.success(
                responseDTO,
                "Orden encontrada exitosamente"

        );
        return ResponseEntity.ok(apiResponse);
    }

    //TODO: voy en el min 43:14, ya cre el controlador completo

    /**
     * Actualiza el estado de una orden (CONFIRMED o CANCELLED).
     * @param id         identificador de la orden a actualizar
     * @param requestDTO DTO con el nuevo estado
     * @param request    objeto HttpServletRequest para extraer el path usado
     * @return ApiResponseDTO con la orden actualizada o error si el estado es inválido
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequestDTO requestDTO,
            HttpServletRequest request) {

        String newStatusStr = requestDTO.getStatus();
        Status newStatus;
        try {
            newStatus = Status.valueOf(newStatusStr);
        } catch (IllegalArgumentException ex) {
            ApiResponseDTO<OrderResponseDTO> apiResponse = ApiResponseDTO.error(
                    "Status inválido: " + newStatusStr,
                    INVALID_ORDER_STATUS,
                    request.getRequestURI()
            );
            return ResponseEntity.badRequest().body(apiResponse);
        }

        // Confirma o cancela, según el valor recibido
        if (newStatus == Status.CONFIRMED) {
            updateOrderStatusService.confirmOrder(id);
        } else if (newStatus == Status.CANCELLED) {
            updateOrderStatusService.cancelOrder(id);
        } else {
            ApiResponseDTO<OrderResponseDTO> apiResponse = ApiResponseDTO.error(
                    "Solo se permite cambiar a CONFIRMED o CANCELLED",
                    UNSUPPORTED_STATUS,
                    request.getRequestURI()
            );
            return ResponseEntity.badRequest().body(apiResponse);
        }

        // Recupera la orden actualizada (esto depende de tu lógica de repositorio)
        Optional<Order> optionalOrder = getOrderService.getOrderById(id);
        if (optionalOrder.isEmpty()) {
            ApiResponseDTO<OrderResponseDTO> apiResponse = ApiResponseDTO.error(
                    "Orden no encontrada después de actualizar", "ORDER_NOT_FOUND", request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        Order updatedOrder = optionalOrder.get();
        List<OrderItemResponseDTO> itemDTOs = OrderMapper.toItemResponseDTOList(updatedOrder.getItems());
        OrderResponseDTO responseDTO = OrderMapper.toResponseDTO(updatedOrder, itemDTOs);

        ApiResponseDTO<OrderResponseDTO> apiResponse = ApiResponseDTO.success(
                responseDTO,
                "Estado de orden actualizado exitosamente"
        );
        return ResponseEntity.ok(apiResponse);
    }

}
