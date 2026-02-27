package edu.ordermanager.application.service;

import edu.ordermanager.domain.exception.CustomerNotFoundException;
import edu.ordermanager.domain.exception.ProductNotFoundException;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.domain.port.in.CreateOrderUseCase;
import edu.ordermanager.domain.port.out.CustomerRepository;
import edu.ordermanager.domain.port.out.OrderRepository;
import edu.ordermanager.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static edu.ordermanager.common.constants.Constants.CUSTOMER_NOT_FOUND;
import static edu.ordermanager.common.constants.Constants.PRODUCT_NOT_FOUND;

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

        // Construir la lista de OrderItem, trayendo el precio actualizado de producto
        List<OrderItem> validatedItems = items.stream().map(oi -> {
            var product = productRepository.findById(oi.getProductId())
                    .orElseThrow(() -> {
                        log.warn("Producto no encontrado: {}", oi.getProductId());
                        return new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, oi.getProductId()));
                    });
            return OrderItem.builder()
                    .productId(oi.getProductId())
                    .quantity(oi.getQuantity())
                    .unitPrice(product.getPrice().getValue())
                    .subtotal(product.getPrice().getValue().multiply(BigDecimal.valueOf(oi.getQuantity())))
                    .build();
        }).collect(Collectors.toList());

        log.debug("Todos los productos validados y precios asignados para la orden.");

        // Crea la orden usando el métod de dominio (valida items)
        Order order = Order.create(customerId, validatedItems);

        Order savedOrder = orderRepository.save(order);

        log.info("Orden creada exitosamente con ID {}", savedOrder.getId());

        return savedOrder;
    }
}
