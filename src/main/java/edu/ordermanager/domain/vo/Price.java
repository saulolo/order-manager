package edu.ordermanager.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

import static edu.ordermanager.common.constants.Constants.NULL_PRICE_ERROR;
import static edu.ordermanager.common.constants.Constants.PRICE_NEGATIVE_ERROR;

/**
 * Value Object para representar y validar un precio.
 */
@Getter
@ToString
@EqualsAndHashCode
public final class Price {

    private final BigDecimal value;


    public Price(BigDecimal value) {
        if (value == null) throw new IllegalArgumentException(NULL_PRICE_ERROR);
        if (value.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException(PRICE_NEGATIVE_ERROR);
        this.value = value;
    }

}
