package edu.ordermanager.domain.port.out;

import edu.ordermanager.domain.model.Order;

import java.io.File;

public interface EmailService {

    /**
     * Envía un email de confirmación cuando una orden es creada.
     * @param order orden recién creada
     */
    void sendOrderCreatedEmail(Order order);

    /**
     * Envía un email de notificación cuando el estado de una orden cambia.
     * @param order orden con el estado actualizado
     */
    void sendOrderStatusUpdatedEmail(Order order);

    /**
     * Envía un email con un archivo adjunto.
     * @param order   orden relacionada
     * @param file    archivo a adjuntar (PDF, imagen, etc.)
     */
    void sendOrderEmailWithAttachment(Order order, File file);

}
