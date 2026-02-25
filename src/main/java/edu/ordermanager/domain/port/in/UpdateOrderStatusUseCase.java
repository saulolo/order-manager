package edu.ordermanager.domain.port.in;

public interface UpdateOrderStatusUseCase {

    /**
     * Confirma una orden.
     *
     * @param orderId ID de la orden a confirmar.
     */
    void confirmOrder(Long orderId);

    /**
     * Cancela una orden.
     *
     * @param orderId ID de la orden a cancelar.
     */
    void cancelOrder(Long orderId);
}
