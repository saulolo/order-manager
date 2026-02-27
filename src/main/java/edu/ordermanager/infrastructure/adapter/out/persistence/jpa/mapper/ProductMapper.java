package edu.ordermanager.infrastructure.adapter.out.persistence.jpa.mapper;

import edu.ordermanager.domain.model.Product;
import edu.ordermanager.domain.vo.Description;
import edu.ordermanager.domain.vo.Price;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.entity.ProductEntity;

import static edu.ordermanager.common.constants.Constants.NON_INSTANTIABLE_UTILITY_CLASS;

/**
 * Mapper para convertir entre entidades JPA de Product y modelos de dominio de Product.
 * Clase de utilidad no instanciable.
 */
public final class ProductMapper {

    private ProductMapper() {
        throw new UnsupportedOperationException(NON_INSTANTIABLE_UTILITY_CLASS);
    }

    // ========== ENTITY → DOMAIN ==========
    /**
     * Convierte una entidad JPA ProductEntity a un modelo de dominio Product.
     *
     * @param entity entidad JPA a convertir, puede ser null.
     * @return objeto Product o null si el parámetro es null.
     */
    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(new Description(entity.getDescription()))
                .price(new Price(entity.getPrice()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    // ========== DOMAIN → ENTITY ==========
    /**
     * Convierte un modelo de dominio Product a una entidad JPA ProductEntity.
     *
     * @param domain modelo de dominio a convertir, puede ser null.
     * @return entidad ProductEntity o null si el parámetro es null.
     */
    public static ProductEntity toEntity(Product domain) {
        if (domain == null) return null;
        return ProductEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription().getValue())
                .price(domain.getPrice().getValue())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
