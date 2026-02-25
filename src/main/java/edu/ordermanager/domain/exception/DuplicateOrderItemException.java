package edu.ordermanager.domain.exception;

/**
 * Excepción lanzada cuando se intenta agregar un ítem duplicado a una orden.
 */
public class DuplicateOrderItemException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado.
     *
     * @param message Descripción del error.
     */
    public DuplicateOrderItemException(String message) {
        super(message);
    }
}