package edu.ordermanager.domain.exception;


public class OrderDomainException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado.
     * @param message Descripción del error.
     */
    public OrderDomainException(String message) {
        super(message);
    }
}