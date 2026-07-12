package edu.ordermanager.infrastructure.adapter.out.email;

import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.port.out.EmailService;
import edu.ordermanager.infrastructure.util.MailTemplateUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceAdapter implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    /**
     * Envía un correo HTML al cliente notificando la creación de una orden.
     * El contenido se genera desde un template con los datos de la orden.
     *
     * @param order orden recién creada
     */
    @Override
    public void sendOrderCreatedEmail(Order order) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setFrom(fromEmail);
            helper.setTo(order.getCustomerEmail());
            helper.setSubject("Orden #" + order.getId() + " creada exitosamente.");

            Map<String, String> params = Map.of(
                    "orderId", String.valueOf(order.getId()),
                    "status", String.valueOf(order.getStatus()),
                    "total", String.valueOf(order.getTotalAmount()),
                    "createdAt", String.valueOf(order.getCreatedAt())
            );

            String htmlBody = MailTemplateUtil.loadAndFillTemplate("order-created.html", params);
            helper.setText(htmlBody, true);

            mailSender.send(message);
            log.info("Email de orden creada enviada a: {}", order.getCustomerEmail());

        } catch (MessagingException e) {
            log.error("Error al enviar email de orden creada: {}", e.getMessage());
        }
    }

    /**
     * Envía un correo HTML al cliente notificando que el estado de una orden cambió.
     * El contenido se genera desde un template con los datos actualizados de la orden.
     *
     * @param order orden con el nuevo estado
     */
    @Override
    public void sendOrderStatusUpdatedEmail(Order order) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setFrom(fromEmail);
            helper.setTo(order.getCustomerEmail());
            helper.setSubject("Estado actualizado para Orden #" + order.getId());

            Map<String, String> params = Map.of(
                    "orderId", String.valueOf(order.getId()),
                    "status", String.valueOf(order.getStatus()),
                    "updatedAt", String.valueOf(order.getUpdatedAt())
            );
            String htmlBody = MailTemplateUtil.loadAndFillTemplate("order-status-updated.html", params);
            helper.setText(htmlBody, true);

            mailSender.send(message);
            log.info("Email de actualización de estado enviado a: {}", order.getCustomerEmail());

        } catch (MessagingException e) {
            log.error("Error al enviar email de actualización de estado: {}", e.getMessage());
        }
    }

    /**
     * Envía un correo HTML con archivo adjunto al cliente, relacionado con la orden.
     * El contenido se genera desde un template y se adjunta el archivo recibido.
     *
     * @param order orden relacionada
     * @param file  archivo a adjuntar (PDF, imagen, etc.)
     */
    @Override
    public void sendOrderEmailWithAttachment(Order order, File file) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setFrom(fromEmail);
            helper.setTo(order.getCustomerEmail());
            helper.setSubject("Documento adjunto para Orden #" + order.getId());

            Map<String, String> params = Map.of(
                    "orderId", String.valueOf(order.getId()),
                    "status", String.valueOf(order.getStatus()),
                    "fileName", file.getName()
            );
            String htmlBody = MailTemplateUtil.loadAndFillTemplate("order-attachment.html", params);
            helper.setText(htmlBody, true);

            // Agrega el adjunto
            helper.addAttachment(file.getName(), file);

            mailSender.send(message);
            log.info("Email con adjunto enviado a: {}", order.getCustomerEmail());

        } catch (MessagingException e) {
            log.error("Error al enviar email con adjunto: {}", e.getMessage());
        }
    }

}
