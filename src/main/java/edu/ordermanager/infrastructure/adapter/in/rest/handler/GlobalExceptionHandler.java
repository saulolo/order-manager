package edu.ordermanager.infrastructure.adapter.in.rest.handler;

import edu.ordermanager.domain.exception.CustomerNotFoundException;
import edu.ordermanager.domain.exception.OrderDomainException;
import edu.ordermanager.domain.exception.OrderNotFoundException;
import edu.ordermanager.infrastructure.adapter.in.rest.controller.dto.response.ApiResponseDTO;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static edu.ordermanager.common.constants.Constants.*;


/**
 * Manejador global de excepciones para toda la aplicación.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones personalizadas de tipo {@link ApiException}.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleApiException(ApiException ex, HttpServletRequest request) {
        log.warn("Error controlado: [{}] {} - Path: {}", ex.getErrorCode(), ex.getMessage(), request.getRequestURI());
        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
                .status(false)
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    /**
     * Maneja CustomerNotFoundException lanzada desde el dominio.
     * <p>
     * Devuelve un error 404 con el código técnico CUSTOMER_NOT_FOUND.
     * @param ex      excepción lanzada cuando el cliente no existe
     * @param request petición HTTP actual
     * @return respuesta de error estandarizada
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleCustomerNotFound(CustomerNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(ApiResponseDTO.error(
                        ex.getMessage(),
                        CUSTOMER_NOT_FOUND,
                        request.getRequestURI()
                ));
    }

    /**
     * Maneja errores de validación de Bean Validation (@Valid).
     * <p>Se lanza cuando un DTO no cumple las restricciones de validación
     * definidas con anotaciones como @NotBlank, @Email, @Size, etc.</p>
     * @param ex      excepción de validación
     * @param request petición HTTP actual
     * @return respuesta con errores campo por campo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        log.warn("Error de validación en: {}", request.getRequestURI());

        // Extraer errores campo por campo
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiResponseDTO<Void> response = ApiResponseDTO.validationError(
                VALIDATION_ERROR_MESSAGE,
                errors,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja OrderNotFoundException lanzada desde el dominio.
     * <p>
     * Devuelve un error 404 con el código técnico ORDER_NOT_FOUND.
     * @param ex      excepción lanzada cuando la orden no existe
     * @param request petición HTTP actual
     * @return respuesta de error estandarizada
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleOrderNotFound(OrderNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(ApiResponseDTO.error(
                        ex.getMessage(),
                        ORDER_NOT_FOUND,
                        request.getRequestURI()
                ));
    }

    /**
     * Maneja excepciones de negocio del dominio de órdenes.
     * <p>
     * Devuelve error 400 (bad request) con el mensaje y detalles de la excepción.
     *
     * @param ex      excepción de dominio lanzada por reglas de Order
     * @param request petición HTTP actual
     * @return respuesta de error estandarizada para violaciones de reglas de negocio
     */
    @ExceptionHandler(OrderDomainException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleOrderDomainException(OrderDomainException ex, HttpServletRequest request) {
        log.warn("Error de dominio en orden: {} - Path: {}", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(
                ApiResponseDTO.error(
                        ex.getMessage(),
                        ORDER_DOMAIN_ERROR,
                        request.getRequestURI()
                )
        );
    }


    /**
     * Maneja errores no controlados (genéricos).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Error inesperado: ", ex);

        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
                .status(false)
                .message(INTERNAL_ERROR_MESSAGE)
                .errorCode(INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
