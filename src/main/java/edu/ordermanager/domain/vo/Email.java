package edu.ordermanager.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

import static edu.ordermanager.common.constants.Constants.EMAIL_FORMAT_ERROR;
import static edu.ordermanager.common.constants.Constants.EMAIL_REQUIRED_ERROR;

@Getter
@ToString
@EqualsAndHashCode
public final class Email {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,10}$");

    private final String value;

    public Email(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(EMAIL_REQUIRED_ERROR);
        }
        String emailNormalized = value.trim().toLowerCase();
        if (!EMAIL_PATTERN.matcher(emailNormalized).matches()) {
            throw new IllegalArgumentException(EMAIL_FORMAT_ERROR);
        }
        this.value = emailNormalized;
    }
}