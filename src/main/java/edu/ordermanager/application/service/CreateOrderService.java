package edu.ordermanager.application.service;

import edu.ordermanager.domain.exception.CustomerNotFoundException;
import edu.ordermanager.domain.exception.ProductNotFoundException;
import edu.ordermanager.domain.model.Customer;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.domain.model.Product;
import edu.ordermanager.domain.port.in.CreateOrderUseCase;
import edu.ordermanager.domain.port.out.CustomerRepository;
import edu.ordermanager.domain.port.out.EmailService;
import edu.ordermanager.domain.port.out.OrderRepository;
import edu.ordermanager.domain.port.out.ProductRepository;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.request.OrderRequestDTO;
import edu.ordermanager.infrastructure.adapter.in.rest.mapper.OrderMapper;
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
    private final EmailService emailService;


    /**
     * Crea una nueva orden si el cliente y los productos existen.
     *
     * @param customerId ID del cliente.
     * @param items Lista de ítems de la orden.
     * @return Orden creada.
     */
    @Override
    public Order createOrder(OrderRequestDTO dto) {
        log.info("Iniciando creación de orden para cliente {}...", dto.getCustomerId());

        // Busca el cliente y saca el email (como String)
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado"));
        String customerEmail = customer.getEmail().getValue();

        // Arma los items validando producto y asignando precios
        List<OrderItem> validatedItems = dto.getItems().stream()
                .map(oi -> {
                    // Busca el producto y saca el precio
                    Product product = productRepository.findById(oi.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado: " + oi.getProductId()));
                    BigDecimal unitPrice = product.getPrice().getValue();
                    BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(oi.getQuantity()));
                    return OrderItem.builder()
                            .productId(oi.getProductId())
                            .quantity(oi.getQuantity())
                            .unitPrice(unitPrice)
                            .subtotal(subtotal)
                            .build();
                })
                .collect(Collectors.toList());

        // Crea y guarda la orden
        Order order = Order.create(dto.getCustomerId(), customerEmail, validatedItems);
        Order savedOrder = orderRepository.save(order);

        log.info("Orden creada exitosamente con ID {}", savedOrder.getId());

        emailService.sendOrderCreatedEmail(savedOrder);
        return savedOrder;
    }
}
