package edu.ordermanager.infrastructure.adapter.out.persistence.jpa.exception;

import org.springframework.http.HttpStatus;

import static edu.ordermanager.common.constants.Constants.CONFLICT;

/**
 * Excepción para conflictos de datos (409).
 * Se lanza cuando hay un conflicto con el estado actual del recurso.
 */
public class ConflictException extends ApiException{

    /**
     * Constructor con mensaje de error.
     *
     * @param message descripción del conflicto
     */
    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT, CONFLICT);
    }

    /**
     * Constructor con mensaje y causa raíz.
     *
     * @param message descripción del conflicto
     * @param cause   excepción original que causó el error
     */
    public ConflictException(String message, Throwable cause) {
        super(message, HttpStatus.CONFLICT, CONFLICT, cause);
    }
}
