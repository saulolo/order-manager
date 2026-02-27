package edu.ordermanager.common.constants;

/**
 * Clase que centraliza las constantes globales de la aplicación.
 */
public final class Constants {

    // ========== CÓDIGOS DE ERROR HTTP ==========
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String CONFLICT = "CONFLICT";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String ORDER_DOMAIN_ERROR = "ORDER_DOMAIN_ERROR";


    // ========== MENSAJES DE ERROR (GlobalExceptionHandler) ==========
    public static final String NON_INSTANTIABLE_UTILITY_CLASS = "Clase de utilidad no instanciable";
    public static final String VALIDATION_ERROR_MESSAGE = "Error de validación en los datos enviados.";
    public static final String INTERNAL_ERROR_MESSAGE = "Ha ocurrido un error inesperado en el servidor.";

    // ========== CÓDIGOS DE ERROR DE NEGOCIO ==========
    public static final String CUSTOMER_NOT_FOUND = "No existe el cliente con ID: %d";
    public static final String PRODUCT_NOT_FOUND = "No existe el producto con ID: %d";
    public static final String ORDER_NOT_FOUND = "No existe la orden con ID: %d";
    public static final String ORDER_MINIMUM_PRODUCT_REQUIRED = "La orden debe de tener al menos un producto.";
    public static final String ORDER_CONFIRMATION_STATE_ERROR = "Solo puede confirmarse una orden en estado CREATED.";
    public static final String ORDER_CANCELLATION_STATE_ERROR = "Solo puede cancelarse una orden en estado CREATED.";
    public static final String NULL_PRICE_ERROR = "El precio no puede ser NULO";
    public static final String PRICE_NEGATIVE_ERROR = "El precio no puede ser negativo.";
    public static final String DESCRIPTION_REQUIRED_ERROR = "La descripción no puede ser vacía.";
    public static final String DESCRIPTION_LENGTH_ERROR = "La descripción debe tener entre 10 y 200 caracteres.";
    public static final String EMAIL_REQUIRED_ERROR = "El email es requerido.";
    public static final String EMAIL_FORMAT_ERROR = "Email inválido.";

    public static final String INVALID_ORDER_STATUS = "Operación inválida para el estado actual de la orden.";
    public static final String UNSUPPORTED_STATUS = "Estado no soportado.";

    // ========== MENSAJES DE ÉXITO ==========
    public static final String ORDER_CREATED = "Orden creada exitosamente.";


    private Constants() {
        throw new UnsupportedOperationException(NON_INSTANTIABLE_UTILITY_CLASS);
    }
}
