package edu.ordermanager.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    @DisplayName("getSubtotal devuelve BigDecimal.ZERO si unitPrice es null")
    void testGetSubtotal_unitPriceNull() {
        OrderItem item = OrderItem.builder()
                .productId(1L)
                .quantity(3)
                .unitPrice(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        assertEquals(BigDecimal.ZERO, item.getSubtotal());
    }

    @Test
    @DisplayName("getSubtotal calcula correctamente el subtotal con unitPrice y quantity válidos")
    void testGetSubtotal_success() {
        OrderItem item = OrderItem.builder()
                .productId(2L)
                .quantity(5)
                .unitPrice(new BigDecimal("20.00"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // subtotal esperado: 5 * 20.00 = 100.00
        assertEquals(new BigDecimal("100.00"), item.getSubtotal());
    }

    @Test
    @DisplayName("Builder y getters funcionan correctamente")
    void testBuilderAndGetters() {
        LocalDateTime date = LocalDateTime.now();
        OrderItem item = OrderItem.builder()
                .productId(8L)
                .quantity(2)
                .unitPrice(new BigDecimal("55.50"))
                .subtotal(new BigDecimal("111.00"))
                .createdAt(date)
                .updatedAt(date)
                .build();

        assertEquals(8L, item.getProductId());
        assertEquals(2, item.getQuantity());
        assertEquals(new BigDecimal("55.50"), item.getUnitPrice());
        assertEquals(new BigDecimal("111.00"), item.getSubtotal());
        assertEquals(date, item.getCreatedAt());
        assertEquals(date, item.getUpdatedAt());
    }
}
