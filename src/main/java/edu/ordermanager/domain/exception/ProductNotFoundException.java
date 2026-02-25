package edu.ordermanager.domain.exception;

/**
 * Excepción lanzada cuando un producto no existe en el sistema.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado.
     *
     * @param message Descripción del error.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
