package edu.ordermanager.infrastructure.adapter.out.persistence.mongo.document;

import edu.ordermanager.domain.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "orders")
public class OrderDocument {

    @Id
    String id;

    Long customerId;
    Status status;
    List<OrderItemDocument> items;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
