package edu.ordermanager.domain.port.out;

import edu.ordermanager.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {


    /**
     * Busca un producto por su identificador.
     *
     * @param productId ID del producto.
     * @return Producto encontrado, o vacío si no existe.
     */
    Optional<Product> findById(Long productId);

}
