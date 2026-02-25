package edu.ordermanager.data;

import edu.ordermanager.domain.enums.Status;
import edu.ordermanager.domain.model.Customer;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataDummy {

    // ====== VALORES POR DEFECTO ======
    public static final Long DEFAULT_ORDER_ID = 1L;
    public static final Long DEFAULT_CUSTOMER_ID = 123L;
    public static final Long DEFAULT_PRODUCT_ID = 456L;
    public static final int DEFAULT_QUANTITY = 2;
    public static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal("1000.00");
    public static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(2026, 2, 25, 10, 0, 0);


    // ====== DUMMY DE OrderItem ======
    /**
     * Crea un OrderItem de prueba con datos por defecto.
     * @return OrderItem con valores dummy.
     */
    public static OrderItem baseOrderItem() {
        return OrderItem.builder()
                .productId(DEFAULT_PRODUCT_ID)
                .quantity(DEFAULT_QUANTITY)
                .unitPrice(DEFAULT_UNIT_PRICE)
                .subtotal(DEFAULT_UNIT_PRICE.multiply(BigDecimal.valueOf(DEFAULT_QUANTITY)))
                .createdAt(DEFAULT_TIME)
                .updatedAt(DEFAULT_TIME)
                .build();
    }

    /**
     * Devuelve una lista con un solo OrderItem dummy.
     * @return Lista de OrderItem para test.
     */
    public static List<OrderItem> orderItemList() {
        return Collections.singletonList(baseOrderItem());
    }

    // ====== DUMMY DE Order ======
    /**
     * Crea un Order de prueba con datos por defecto y un OrderItem dummy.
     * @return Order con valores dummy.
     */
    public static Order baseOrder() {
        return Order.builder()
                .id(DEFAULT_ORDER_ID)
                .customerId(DEFAULT_CUSTOMER_ID)
                .items(orderItemList())
                .createdAt(DEFAULT_TIME)
                .updatedAt(DEFAULT_TIME)
                .build();
    }

    /**
     * Devuelve una lista con un solo Order dummy.
     * @return Lista de Order para test.
     */
    public static List<Order> orderDummyList() {
        return Arrays.asList(baseOrder());
    }

    /**
     * Crea un Order de prueba con estado CREATED y datos por defecto.
     * @return Order con estado CREATED.
     */
    public static Order orderCreated() {
        return Order.builder()
                .id(DEFAULT_ORDER_ID)
                .customerId(DEFAULT_CUSTOMER_ID)
                .items(orderItemList())
                .status(Status.CREATED)
                .createdAt(DEFAULT_TIME)
                .updatedAt(DEFAULT_TIME)
                .build();
    }

    /**
     * Crea un Order de prueba con estado CONFIRMED y datos por defecto.
     * @return Order con estado CONFIRMED.
     */
    public static Order orderConfirmed() {
        return Order.builder()
                .id(DEFAULT_ORDER_ID)
                .customerId(DEFAULT_CUSTOMER_ID)
                .items(orderItemList())
                .status(Status.CONFIRMED)
                .createdAt(DEFAULT_TIME)
                .updatedAt(DEFAULT_TIME)
                .build();
    }

    /**
     * Crea un Order de prueba con estado CANCELLED y datos por defecto.
     * @return Order con estado CANCELLED.
     */
    public static Order orderCancelled() {
        return Order.builder()
                .id(DEFAULT_ORDER_ID)
                .customerId(DEFAULT_CUSTOMER_ID)
                .items(orderItemList())
                .status(Status.CANCELLED)
                .createdAt(DEFAULT_TIME)
                .updatedAt(DEFAULT_TIME)
                .build();
    }


    // ====== DUMMY DE Customer ======
    /**
     * Crea un Customer de prueba con datos por defecto.
     * @return Customer con valores dummy.
     */
    public static Customer baseCustomer() {
        return Customer.builder()
                .id(DEFAULT_CUSTOMER_ID)
                .fullName("Jane Doe")
                .email("jane.doe@test.com")
                .createdAt(DEFAULT_TIME)
                .updatedAt(DEFAULT_TIME)
                .build();
    }

    // ====== DUMMY DE Product ======
    /**
     * Crea un Product de prueba con datos por defecto.
     * @return Product con valores dummy.
     */
    public static Product baseProduct() {
        return Product.builder()
                .id(DEFAULT_PRODUCT_ID)
                .name("Producto Test")
                .description("Descripción de prueba")
                .price(DEFAULT_UNIT_PRICE)
                .createdAt(DEFAULT_TIME)
                .updatedAt(DEFAULT_TIME)
                .build();
    }



}

