package edu.ordermanager.application.service;

import edu.ordermanager.data.DataDummy.*;
import edu.ordermanager.domain.model.Customer;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.domain.model.Product;
import edu.ordermanager.domain.port.out.CustomerRepository;
import edu.ordermanager.domain.port.out.OrderRepository;
import edu.ordermanager.domain.port.out.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static edu.ordermanager.data.DataDummy.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateOrderServiceTest - Pruebas Unitarias")
class CreateOrderServiceTest {

    @Mock
    private  OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateOrderService createOrderService;

    private Order order;
    private List<OrderItem> orderItems;
    private Customer customer;
    private Product product;

    @BeforeEach
    void setUp() {
        order = baseOrder();
        orderItems = orderItemList();
        customer = baseCustomer();
        product = baseProduct();
    }

    @Test
    @DisplayName("Debe crear una orden correctamente cuando todos los datos existen")
    void testCreateOrder_success() {
        // GIVEN
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // WHEN
        Order result = createOrderService.createOrder(DEFAULT_CUSTOMER_ID, orderItems);

        // THEN
        assertNotNull(result, "La orden no puede ser nula.");
        assertEquals(DEFAULT_ORDER_ID, result.getId());
        assertEquals(DEFAULT_CUSTOMER_ID, result.getCustomerId());
        assertEquals(orderItems.size(), result.getItems().size());

        verify(orderRepository).save(any(Order.class));
        verify(customerRepository).findById(DEFAULT_CUSTOMER_ID);
        verify(productRepository, atLeastOnce()).findById(any(Long.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción si la lista de ítems es null")
    void testCreateOrder_itemsNull() {
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(Optional.of(customer));

        assertThrows(
                edu.ordermanager.domain.exception.OrderItemsEmptyException.class,
                () -> createOrderService.createOrder(DEFAULT_CUSTOMER_ID, null)
        );

        verify(customerRepository).findById(DEFAULT_CUSTOMER_ID);
        verify(productRepository, never()).findById(any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el cliente no existe")
    void testCreateOrder_customerNotFound() {
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(Optional.empty());

        assertThrows(
                edu.ordermanager.domain.exception.CustomerNotFoundException.class,
                () -> createOrderService.createOrder(DEFAULT_CUSTOMER_ID, orderItems)
        );

        verify(customerRepository).findById(DEFAULT_CUSTOMER_ID);
        verify(productRepository, never()).findById(any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si la lista de ítems está vacía")
    void testCreateOrder_itemsEmpty() {
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(Optional.of(customer));

        List<OrderItem> emptyItems = List.of();

        assertThrows(
                edu.ordermanager.domain.exception.OrderItemsEmptyException.class,
                () -> createOrderService.createOrder(DEFAULT_CUSTOMER_ID, emptyItems)
        );

        verify(customerRepository).findById(DEFAULT_CUSTOMER_ID);
        verify(productRepository, never()).findById(any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si algún producto no existe")
    void testCreateOrder_productNotFound() {
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(
                edu.ordermanager.domain.exception.ProductNotFoundException.class,
                () -> createOrderService.createOrder(DEFAULT_CUSTOMER_ID, orderItems)
        );

        verify(customerRepository).findById(DEFAULT_CUSTOMER_ID);
        verify(productRepository, atLeastOnce()).findById(any(Long.class));
        verify(orderRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si uno de varios productos no existe")
    void testCreateOrder_oneProductNotFoundAmongMany() {
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(Optional.of(customer));

        OrderItem existingItem = orderItems.get(0);
        OrderItem nonExistingItem = OrderItem.builder()
                .productId(999L)
                .quantity(1)
                .unitPrice(DEFAULT_UNIT_PRICE)
                .subtotal(DEFAULT_UNIT_PRICE)
                .createdAt(DEFAULT_TIME)
                .updatedAt(DEFAULT_TIME)
                .build();
        List<OrderItem> mixedItems = List.of(existingItem, nonExistingItem);

        when(productRepository.findById(DEFAULT_PRODUCT_ID)).thenReturn(Optional.of(product));
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(
                edu.ordermanager.domain.exception.ProductNotFoundException.class,
                () -> createOrderService.createOrder(DEFAULT_CUSTOMER_ID, mixedItems)
        );

        verify(customerRepository).findById(DEFAULT_CUSTOMER_ID);
        verify(productRepository).findById(DEFAULT_PRODUCT_ID);
        verify(productRepository).findById(999L);
        verify(orderRepository, never()).save(any());
    }

}
