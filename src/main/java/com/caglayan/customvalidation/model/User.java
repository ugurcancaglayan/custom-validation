package com.caglayan.customvalidation.model;

import com.caglayan.customvalidation.annotations.CustomValidation;
import com.caglayan.customvalidation.enumeration.ValidationType;

public record User(
        Long id,
        String email,

        @CustomValidation(message = "Invalid password", type = ValidationType.PASSWORD)
        String password,

        @CustomValidation(message = "Invalid phone number", type = ValidationType.PHONE_NUMBER)
        String phoneNumber) {
}
