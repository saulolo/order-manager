package edu.ordermanager.application.service;

import edu.ordermanager.domain.enums.Status;
import edu.ordermanager.domain.exception.OrderNotFoundException;
import edu.ordermanager.domain.exception.OrderStatusException;
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

import static edu.ordermanager.data.DataDummy.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UpdateOrderStatusServiceTest - Pruebas Unitarias")
class UpdateOrderStatusServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private UpdateOrderStatusService updateOrderStatusService;

    private Order orderCreated;
    private Order orderConfirmed;
    private Order orderCancelled;

    @BeforeEach
    void setUp() {
        orderCreated = orderCreated();
        orderConfirmed = orderConfirmed();
        orderCancelled = orderCancelled();
    }


    @Test
    @DisplayName("Debe confirmar una orden correctamente si está en estado CREATED")
    void testConfirmOrder_success() {
        when(orderRepository.findById(DEFAULT_ORDER_ID)).thenReturn(Optional.of(orderCreated));

        updateOrderStatusService.confirmOrder(DEFAULT_ORDER_ID);

        assertEquals(Status.CONFIRMED, orderCreated.getStatus());
        verify(orderRepository).update(orderCreated);
    }

    @Test
    @DisplayName("Debe lanzar excepción si la orden a confirmar no existe")
    void testConfirmOrder_notFound() {
        when(orderRepository.findById(DEFAULT_ORDER_ID)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class,
                () -> updateOrderStatusService.confirmOrder(DEFAULT_ORDER_ID));

        verify(orderRepository, never()).update(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si la orden a confirmar no está en estado CREATED")
    void testConfirmOrder_invalidStatus() {
        when(orderRepository.findById(DEFAULT_ORDER_ID)).thenReturn(Optional.of(orderConfirmed)); // Ya confirmada

        assertThrows(OrderStatusException.class,
                () -> updateOrderStatusService.confirmOrder(DEFAULT_ORDER_ID));

        verify(orderRepository, never()).update(any());
    }

    // =========================
    // ---- CANCELAR ORDEN ----
    // =========================

    @Test
    @DisplayName("Debe cancelar una orden correctamente si está en estado CREATED")
    void testCancelOrder_success() {
        when(orderRepository.findById(DEFAULT_ORDER_ID)).thenReturn(Optional.of(orderCreated));

        updateOrderStatusService.cancelOrder(DEFAULT_ORDER_ID);

        assertEquals(Status.CANCELLED, orderCreated.getStatus());
        verify(orderRepository).update(orderCreated);
    }

    @Test
    @DisplayName("Debe lanzar excepción si la orden a cancelar no existe")
    void testCancelOrder_notFound() {
        when(orderRepository.findById(DEFAULT_ORDER_ID)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class,
                () -> updateOrderStatusService.cancelOrder(DEFAULT_ORDER_ID));

        verify(orderRepository, never()).update(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si la orden a cancelar no está en estado CREATED")
    void testCancelOrder_invalidStatus() {
        when(orderRepository.findById(DEFAULT_ORDER_ID)).thenReturn(Optional.of(orderCancelled)); // Ya cancelada

        assertThrows(OrderStatusException.class,
                () -> updateOrderStatusService.cancelOrder(DEFAULT_ORDER_ID));

        verify(orderRepository, never()).update(any());
    }
}