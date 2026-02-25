package edu.ordermanager.infrastructure.adapter.out.persistence.jpa.repository;

import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
}
