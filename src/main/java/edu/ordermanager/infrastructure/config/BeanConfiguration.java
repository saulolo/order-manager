package edu.ordermanager.infrastructure.config;

import edu.ordermanager.application.service.CreateOrderService;
import edu.ordermanager.application.service.GetOrderService;
import edu.ordermanager.application.service.UpdateOrderStatusService;
import edu.ordermanager.domain.port.out.CustomerRepository;
import edu.ordermanager.domain.port.out.OrderRepository;
import edu.ordermanager.domain.port.out.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura manualmente los servicios de aplicación como beans de Spring.
 */
@Configuration
public class BeanConfiguration {

    /**
     * Bean para el servicio de creación de órdenes.
     *
     * @param orderRepository repositorio de órdenes
     * @param customerRepository repositorio de clientes
     * @param productRepository repositorio de productos
     * @return instancia de CreateOrderService gestionada por Spring
     */
    @Bean
    public CreateOrderService createOrderService(OrderRepository orderRepository,
                                                 CustomerRepository customerRepository,
                                                 ProductRepository productRepository) {
        return new CreateOrderService(orderRepository, customerRepository, productRepository);
    }

    /**
     * Bean para el servicio de consulta de órdenes.
     *
     * @param orderRepository repositorio de órdenes
     * @return instancia de GetOrderService gestionada por Spring
     */
    @Bean
    public GetOrderService getOrderService(OrderRepository orderRepository) {
        return new GetOrderService(orderRepository);
    }

    /**
     * Bean para el servicio de actualización de estado de órdenes.
     *
     * @param orderRepository repositorio de órdenes
     * @return instancia de UpdateOrderStatusService gestionada por Spring
     */
    @Bean
    public UpdateOrderStatusService updateOrderStatusService(OrderRepository orderRepository) {
        return new UpdateOrderStatusService(orderRepository);
    }
}
