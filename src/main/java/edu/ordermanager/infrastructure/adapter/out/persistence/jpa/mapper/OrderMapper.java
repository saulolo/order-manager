package edu.ordermanager.infrastructure.adapter.out.persistence.jpa.mapper;

import edu.ordermanager.domain.enums.Status;
import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.model.OrderItem;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.CustomerEntity;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.OrderEntity;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.OrderItemEntity;

import java.util.List;
import java.util.stream.Collectors;

import static edu.ordermanager.common.constants.Constants.NON_INSTANTIABLE_UTILITY_CLASS;

/**
 * Mapper para convertir entre entidades JPA de Order y modelos de dominio Order.
 * Clase de utilidad no instanciable.
 */
public final class OrderMapper {

    private OrderMapper() {
        throw new UnsupportedOperationException(NON_INSTANTIABLE_UTILITY_CLASS);
    }

    // ========== ENTITY → DOMAIN ==========
    /**
     * Convierte una entidad JPA OrderEntity a un modelo de dominio Order.
     *
     * @param entity entidad JPA a convertir, puede ser null.
     * @return modelo de dominio Order o null si el parámetro es null.
     */
    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;
        List<OrderItem> items = entity.getItems() == null ? null :
                entity.getItems().stream()
                        .map(OrderItemMapper::toDomain)
                        .collect(Collectors.toList());

        return Order.builder()
                .id(entity.getId())
                .customerId(entity.getCustomer() != null ? entity.getCustomer().getId() : null)
                .status(entity.getStatus())
                .items(items)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    /**
     * Convierte una lista de entidades JPA OrderEntity a una lista de modelos de dominio Order.
     *
     * @param entities lista de entidades JPA, puede ser null.
     * @return lista de modelos de dominio Order, o null si el parámetro es null.
     */
    public static List<Order> toDomainList(List<OrderEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    // ========== DOMAIN → ENTITY ==========
    /**
     * Convierte un modelo de dominio Order a una entidad JPA OrderEntity.
     *
     * @param domain modelo de dominio a convertir, puede ser null.
     * @param customer entidad Customer asociada.
     * @param items lista de entidades OrderItem asociadas.
     * @return entidad JPA OrderEntity o null si domain es null.
     */
    public static OrderEntity toEntity(Order domain, CustomerEntity customer, List<OrderItemEntity> items) {
        if (domain == null) return null;
        return OrderEntity.builder()
                .id(domain.getId())
                .customer(customer)
                .status(domain.getStatus() != null ? domain.getStatus() : Status.CREATED)
                .items(items)
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }


    /**
     * Convierte una lista de modelos de dominio Order a una lista de entidades JPA OrderEntity.
     *
     * @param domains lista de modelos de dominio, puede ser null.
     * @param customerMap mapa de customerId a entidades Customer.
     * @param orderItemsMap mapa de orderId a listas de entidades OrderItem.
     * @return lista de entidades JPA OrderEntity, o null si domains es null.
     */
    public static List<OrderEntity> toEntityList(
            List<Order> domains,
            java.util.Map<Long, CustomerEntity> customerMap,
            java.util.Map<Long, List<OrderItemEntity>> orderItemsMap
    ) {
        if (domains == null) return null;
        return domains.stream()
                .map(order -> OrderMapper.toEntity(
                        order,
                        customerMap.get(order.getCustomerId()),
                        orderItemsMap.get(order.getId())
                ))
                .collect(Collectors.toList());
    }
}

