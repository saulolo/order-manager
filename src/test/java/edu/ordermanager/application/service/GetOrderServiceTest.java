package edu.ordermanager.application.service;

import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.port.out.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static edu.ordermanager.data.DataDummy.DEFAULT_ORDER_ID;
import static edu.ordermanager.data.DataDummy.baseOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetOrderServiceTest - Pruebas Unitarias")
class GetOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private GetOrderService getOrderService;

    private Order order;

    @BeforeEach
    void setUp() {
        order = baseOrder();
    }

    @Test
    @DisplayName("Debe retornar un Optional con la orden si existe")
    void testGetOrderById_found() {
        when(orderRepository.findById(DEFAULT_ORDER_ID)).thenReturn(Optional.of(order));

        Optional<Order> result = getOrderService.getOrderById(DEFAULT_ORDER_ID);

        assertTrue(result.isPresent());
        assertEquals(DEFAULT_ORDER_ID, result.get().getId());
        verify(orderRepository).findById(DEFAULT_ORDER_ID);
    }

    @Test
    @DisplayName("Debe retornar un Optional vacío si la orden no existe")
    void testGetOrderById_notFound() {
        when(orderRepository.findById(DEFAULT_ORDER_ID)).thenReturn(Optional.empty());

        Optional<Order> result = getOrderService.getOrderById(DEFAULT_ORDER_ID);

        assertFalse(result.isPresent());
        verify(orderRepository).findById(DEFAULT_ORDER_ID);
    }
}