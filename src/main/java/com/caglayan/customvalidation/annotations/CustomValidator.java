package com.caglayan.customvalidation.annotations;

import com.caglayan.customvalidation.enumeration.ValidationType;
import com.caglayan.customvalidation.exception.ObjectNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CustomValidator implements ConstraintValidator<CustomValidation, String> {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\+90\\s\\d{3}\\s\\d{3}\\s\\d{4}$");
    private static final int MIN_PASSWORD_LENGTH = 5;

    private ValidationType type;

    @Override
    public void initialize(CustomValidation constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            throw new ObjectNotFoundException("Value cannot be null");
        }

        return switch (type) {
            case PHONE_NUMBER -> PHONE_NUMBER_PATTERN.matcher(value).matches();
            case PASSWORD -> value.length() >= MIN_PASSWORD_LENGTH;
        };
    }
}