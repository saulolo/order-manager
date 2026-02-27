package edu.ordermanager.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static edu.ordermanager.common.constants.Constants.DESCRIPTION_LENGTH_ERROR;
import static edu.ordermanager.common.constants.Constants.DESCRIPTION_REQUIRED_ERROR;

/**
 * Value Object para representar y validar una descripción.
 */
@Getter
@ToString
@EqualsAndHashCode
public final class Description {

    private final String value;

    public Description(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(DESCRIPTION_REQUIRED_ERROR);
        }
        if (value.length() < 10 || value.length() > 200) {
            throw new IllegalArgumentException(DESCRIPTION_LENGTH_ERROR);
        }
        this.value = value;
    }

}
