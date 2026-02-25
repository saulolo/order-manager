package edu.ordermanager.infrastructure.adapter.out.persistence.jpa;

import edu.ordermanager.domain.model.Product;
import edu.ordermanager.domain.port.out.ProductRepository;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.mapper.ProductMapper;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;


    /**
     * Busca un producto por su identificador.
     *
     * @param id ID del producto.
     * @return El producto encontrado, o vacío si no existe.
     */
    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id)
                .map(ProductMapper::toDomain);
    }
}
