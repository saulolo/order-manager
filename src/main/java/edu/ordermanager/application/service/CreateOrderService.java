package edu.ordermanager.application.service;

import edu.ordermanager.domain.exception.CustomerNotFoundException;
import edu.ordermanager.domain.exception.OrderItemsEmptyException;
import edu.ordermanager.domain.exception.ProductNotFoundException;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.domain.port.in.CreateOrderUseCase;
import edu.ordermanager.domain.port.out.CustomerRepository;
import edu.ordermanager.domain.port.out.OrderRepository;
import edu.ordermanager.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static edu.ordermanager.common.constants.Constants.*;

@Slf4j
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    /**
     * Crea una nueva orden si el cliente y los productos existen.
     *
     * @param customerId ID del cliente.
     * @param items Lista de ítems de la orden.
     * @return Orden creada.
     */
    @Override
    public Order createOrder(Long customerId, List<OrderItem> items) {

        log.info("Iniciando creación de orden para cliente {} con {} ítems.", customerId, items == null ? 0 : items.size());

        // Validar existencia de cliente
        customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    log.warn("Cliente no encontrado: {}", customerId);
                    return new CustomerNotFoundException(String.format(CUSTOMER_NOT_FOUND, customerId));
                });

        // Validar que tenga ítems
        if (items == null || items.isEmpty()) {
            log.warn("Intento de crear orden sin ítems para cliente {}", customerId);
            throw new OrderItemsEmptyException(ORDER_ITEMS_EMPTY);
        }

        // Validar existencia de productos y filtrar posibles duplicados o ítems inválidos
        List<OrderItem> validatedItems = items.stream().map(oi -> {
            productRepository.findById(oi.getProductId())
                    .orElseThrow(() -> {
                        log.warn("Producto no encontrado: {}", oi.getProductId());
                        return new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, oi.getProductId()));
                    });
            return oi;
        }).collect(Collectors.toList());

        log.debug("Todos los productos validados correctamente para la orden.");

        // Crear y guardar la orden
        Order order = Order.builder()
                .customerId(customerId)
                .items(validatedItems)
                .build();

        Order savedOrder = orderRepository.save(order);

        log.info("Orden creada exitosamente con ID {}", savedOrder.getId());

        return savedOrder;
    }
}
