package edu.ordermanager.infrastructure.adapter.out.persistence.mongo;

import edu.ordermanager.domain.model.Order;
import edu.ordermanager.domain.port.out.OrderRepository;
import edu.ordermanager.infrastructure.adapter.out.persistence.mongo.mapper.OrderMongoMapper;
import edu.ordermanager.infrastructure.adapter.out.persistence.mongo.repository.OrderMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderMongoRepositoryAdapter implements OrderRepository {

    private final OrderMongoRepository orderMongoRepository;

    @Override
    public Order save(Order order) {
        var document = OrderMongoMapper.toDocument(order);
        var saved = orderMongoRepository.save(document);
        return OrderMongoMapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderMongoRepository.findById(String.valueOf(id))
                .map(OrderMongoMapper::toDomain);
    }

    @Override
    public void update(Order order) {
        save(order);
    }
}
