package edu.ordermanager.infrastructure.adapter.out.persistence.jpa.mapper;

import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.OrderItemEntity;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.ProductEntity;

import static edu.ordermanager.common.constants.Constants.NON_INSTANTIABLE_UTILITY_CLASS;

/**
 * Mapper para convertir entre entidades JPA de OrderItem y modelos de dominio OrderItem.
 * Clase de utilidad no instanciable.
 */
public final class OrderItemMapper {

    private OrderItemMapper() {
        throw new UnsupportedOperationException(NON_INSTANTIABLE_UTILITY_CLASS);
    }

    // ========== ENTITY → DOMAIN ==========
    /**
     * Convierte una entidad JPA OrderItemEntity a un modelo de dominio OrderItem.
     *
     * @param entity entidad JPA a convertir, puede ser null.
     * @return modelo de dominio OrderItem o null si el parámetro es null.
     */
    public static OrderItem toDomain(OrderItemEntity entity) {
        if (entity == null) return null;
        return OrderItem.builder()
                .productId(entity.getProduct() != null ? entity.getProduct().getId() : null)
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    // ========== DOMAIN → ENTITY ==========
    /**
     * Convierte un modelo de dominio OrderItem a una entidad JPA OrderItemEntity.
     *
     * @param domain modelo de dominio a convertir, puede ser null.
     * @param product entidad Product asociada (usada como relación JPA).
     * @return entidad OrderItemEntity o null si domain es null.
     */
    public static OrderItemEntity toEntity(OrderItem domain, ProductEntity product) {
        if (domain == null) return null;
        return OrderItemEntity.builder()
                .product(product)
                .quantity(domain.getQuantity())
                .unitPrice(domain.getUnitPrice())
                .subtotal(domain.getSubtotal())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}