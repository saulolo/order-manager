package edu.ordermanager.domain.port.out;

import edu.ordermanager.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {


    /**
     * Guarda una orden en el repositorio.
     *
     * @param order Orden a guardar.
     * @return La orden persistida.
     */
    Order save(Order order);

    /**
     * Busca una orden por su identificador.
     *
     * @param id ID de la orden.
     * @return Orden encontrada, o vacía si no existe.
     */
    Optional<Order> findById(Long id);

    /**
     * Actualiza una orden existente en el repositorio.
     *
     * @param order Orden a actualizar.
     */
    void update(Order order);

}
