package edu.ordermanager.infrastructure.adapter.out.persistence.jpa.repository;

import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
