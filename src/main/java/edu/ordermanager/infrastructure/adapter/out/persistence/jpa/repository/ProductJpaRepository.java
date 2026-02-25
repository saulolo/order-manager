package edu.ordermanager.infrastructure.adapter.out.persistence.jpa.repository;

import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository  extends JpaRepository<ProductEntity, Long> {
}
