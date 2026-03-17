package edu.ordermanager.infrastructure.adapter.out.persistence.mongo.repository;

import edu.ordermanager.infrastructure.adapter.out.persistence.mongo.document.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepository extends MongoRepository<OrderDocument, String> {
}
