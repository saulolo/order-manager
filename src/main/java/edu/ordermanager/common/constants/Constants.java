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

    // ========== MENSAJES DE ERROR (GlobalExceptionHandler) ==========
    public static final String NON_INSTANTIABLE_UTILITY_CLASS = "Clase de utilidad no instanciable";
    public static final String VALIDATION_ERROR_MESSAGE = "Error de validación en los datos enviados.";
    public static final String INTERNAL_ERROR_MESSAGE = "Ha ocurrido un error inesperado en el servidor.";

    // ========== CÓDIGOS DE ERROR DE NEGOCIO ==========
    public static final String CUSTOMER_NOT_FOUND = "No existe el cliente con ID: %d";
    public static final String PRODUCT_NOT_FOUND = "No existe el producto con ID: %d";
    public static final String ORDER_NOT_FOUND = "No existe la orden con ID: %d";
    public static final String ORDER_ITEMS_EMPTY = "No se puede crear una orden sin ítems.";
    public static final String INVALID_ORDER_STATUS = "Operación inválida para el estado actual de la orden.";
    public static final String UNSUPPORTED_STATUS = "Estado no soportado.";

    // ========== MENSAJES DE ÉXITO ==========
    public static final String ORDER_CREATED = "Orden creada exitosamente.";


    private Constants() {
        throw new UnsupportedOperationException(NON_INSTANTIABLE_UTILITY_CLASS);
    }
}
