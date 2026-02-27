package edu.ordermanager.domain.model;

import edu.ordermanager.domain.vo.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Representa un cliente del sistema.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    Long id;
    String fullName;
    Email email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
