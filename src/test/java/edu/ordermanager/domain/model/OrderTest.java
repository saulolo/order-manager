package edu.ordermanager.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    @DisplayName("getTotalAmount devuelve cero si items es null")
    void testGetTotalAmount_itemsNull() {
        Order order = Order.builder().items(null).build();
        assertEquals(BigDecimal.ZERO, order.getTotalAmount());
    }

    @Test
    @DisplayName("getTotalAmount devuelve cero si items está vacío")
    void testGetTotalAmount_itemsEmpty() {
        Order order = Order.builder().items(List.of()).build();
        assertEquals(BigDecimal.ZERO, order.getTotalAmount());
    }

    @Test
    @DisplayName("getTotalAmount suma los subtotales de los items")
    void testGetTotalAmount_success() {
        OrderItem item1 = OrderItem.builder().unitPrice(new BigDecimal("10")).quantity(2).build();
        OrderItem item2 = OrderItem.builder().unitPrice(new BigDecimal("5")).quantity(4).build();
        Order order = Order.builder().items(List.of(item1, item2)).build();

        assertEquals(new BigDecimal("40"), order.getTotalAmount());
    }
}