package edu.ordermanager.domain.exception;

/**
 * Excepción lanzada cuando una orden no existe en el sistema.
 */
public class OrderNotFoundException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado.
     *
     * @param message Descripción del error.
     */
    public OrderNotFoundException(String message) {
        super(message);
    }
}
