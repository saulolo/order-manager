package edu.ordermanager.infrastructure.adapter.out.persistence.jpa;

import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.port.out.OrderRepository;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.CustomerEntity;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.OrderEntity;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.OrderItemEntity;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.ProductEntity;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.mapper.OrderItemMapper;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.mapper.OrderMapper;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.repository.CustomerJpaRepository;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.repository.OrderJpaRepository;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderJpaRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    /**
     * Guarda o actualiza una orden, persistiendo en cascada los items asociados.
     *
     * @param order La orden a guardar.
     * @return La orden persistida.
     */
    @Override
    public Order save(Order order) {
        // Buscar customer entity
        CustomerEntity customer = customerJpaRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Mapear items a entidades
        List<OrderItemEntity> itemEntities = null;
        if (order.getItems() != null) {
            itemEntities = order.getItems().stream()
                    .map(item -> {
                        ProductEntity product = productJpaRepository.findById(item.getProductId())
                                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                        return OrderItemMapper.toEntity(item, product);
                    })
                    .collect(Collectors.toList());
        }

        // Mapear orden a OrderEntity
        OrderEntity entity = OrderMapper.toEntity(order, customer, itemEntities);

        // Guardar orden y items en cascada
        OrderEntity saved = orderJpaRepository.save(entity);

        // Devolver dominio mapeado
        return OrderMapper.toDomain(saved);
    }

    /**
     * Busca una orden por su identificador.
     *
     * @param id ID de la orden.
     * @return La orden encontrada, o vacío si no existe.
     */
    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id)
                .map(OrderMapper::toDomain);
    }

    /**
     * Actualiza una orden existente en el repositorio.
     * En realidad, usa save() porque JPA maneja create/update automáticamente.
     *
     * @param order Orden a actualizar.
     */
    @Override
    public void update(Order order) {
        save(order);
    }
}

