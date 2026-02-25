package edu.ordermanager.domain.port.out;

import edu.ordermanager.domain.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    /**
     * Busca un cliente por su identificador.
     *
     * @param customerId ID del cliente.
     * @return Cliente encontrado, o vacío si no existe.
     */
    Optional<Customer> findById(Long customerId);

}
