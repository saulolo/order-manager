package edu.ordermanager.domain.exception;

/**
 * Excepción lanzada cuando se intenta realizar una operación inválida sobre el estado de una orden.
 */
public class OrderStatusException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado.
     *
     * @param message Descripción del error.
     */
    public OrderStatusException(String message) {
        super(message);
    }
}