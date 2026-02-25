package edu.ordermanager.infrastructure.adapter.out.persistence.jpa;

import edu.ordermanager.domain.model.Customer;
import edu.ordermanager.domain.port.out.CustomerRepository;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.mapper.CustomerMapper;
import edu.ordermanager.infrastructure.adapter.out.persistence.jpa.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerJpaRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;


    /**
     * Busca un cliente por su identificador.
     *
     * @param id ID del cliente.
     * @return Cliente encontrado, o vacío si no existe.
     */
    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findById(id)
                .map(CustomerMapper::toDomain);
    }
}
