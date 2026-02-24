package edu.ordermanager.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Representa un cliente del sistema.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    Long id;
    String fullName;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
