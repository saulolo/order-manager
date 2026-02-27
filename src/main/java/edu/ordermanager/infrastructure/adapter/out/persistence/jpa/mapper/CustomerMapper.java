package edu.ordermanager.infrastructure.adapter.out.persistence.jpa.mapper;

import edu.ordermanager.domain.model.Customer;
import edu.ordermanager.domain.vo.Email;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.CustomerEntity;

import static edu.ordermanager.common.constants.Constants.NON_INSTANTIABLE_UTILITY_CLASS;

/**
 * Mapper para convertir entre entidades JPA de Customer y modelos de dominio de Customer.
 * Esta clase no puede ser instanciada.
 */
public final class CustomerMapper {

    private CustomerMapper() {
        throw new UnsupportedOperationException(NON_INSTANTIABLE_UTILITY_CLASS);
    }

    // ========== ENTITY → DOMAIN ==========

    /**
     * Convierte una entidad JPA CustomerEntity a un modelo de dominio Customer.
     *
     * @param entity la entidad JPA a convertir, puede ser null.
     * @return una instancia de Customer, o null si el parámetro es null.
     */
    public static Customer toDomain(CustomerEntity entity) {
        if (entity == null) return null;
        return Customer.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .email(new Email(entity.getEmail()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    // ========== DOMAIN → ENTITY ==========

    /**
     * Convierte un modelo de dominio Customer a una entidad JPA CustomerEntity.
     *
     * @param domain el objeto de dominio a convertir, puede ser null.
     * @return una instancia de CustomerEntity, o null si el parámetro es null.
     */
    public static CustomerEntity toEntity(Customer domain) {
        if (domain == null) return null;
        return CustomerEntity.builder()
                .id(domain.getId())
                .fullName(domain.getFullName())
                .email(domain.getEmail().getValue())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}

