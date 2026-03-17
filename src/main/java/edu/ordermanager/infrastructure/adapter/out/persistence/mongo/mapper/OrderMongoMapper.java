package edu.ordermanager.infrastructure.adapter.out.persistence.mongo.mapper;

import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.infrastructure.adapter.out.persistence.mongo.document.OrderDocument;
import edu.ordermanager.infrastructure.adapter.out.persistence.mongo.document.OrderItemDocument;

import java.util.List;
import java.util.stream.Collectors;

import static edu.ordermanager.common.constants.Constants.NON_INSTANTIABLE_UTILITY_CLASS;

public final class OrderMongoMapper {

    private OrderMongoMapper() {
        throw new UnsupportedOperationException(NON_INSTANTIABLE_UTILITY_CLASS);
    }

    // ========== DOMAIN → DOCUMENT ==========
    public static OrderDocument toDocument(Order order) {
        if (order == null) return null;

        List<OrderItemDocument> itemDocs = order.getItems().stream()
                .map(item -> OrderItemDocument.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .collect(Collectors.toList());

        return OrderDocument.builder()
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .items(itemDocs)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    // ========== DOCUMENT → DOMAIN ==========
    public static Order toDomain(OrderDocument doc) {
        if (doc == null) return null;

        List<OrderItem> items = doc.getItems().stream()
                .map(item -> OrderItem.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .collect(Collectors.toList());

        return Order.builder()
                .customerId(doc.getCustomerId())
                .status(doc.getStatus())
                .items(items)
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}