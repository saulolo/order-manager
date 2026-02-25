package edu.ordermanager.domain.exception;

/**
 * Excepción lanzada cuando un cliente no existe en el sistema.
 */
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado.
     * @param message Descripción del error.
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}