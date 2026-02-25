package edu.ordermanager.domain.exception;

/**
 * Excepción lanzada cuando se intenta crear una orden sin ítems.
 */
public class OrderItemsEmptyException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado.
     *
     * @param message Descripción del error.
     */
    public OrderItemsEmptyException(String message) {
        super(message);
    }
}