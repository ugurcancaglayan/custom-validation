package com.caglayan.customvalidation.annotations;

import com.caglayan.customvalidation.enumeration.ValidationType;
import com.caglayan.customvalidation.exception.ObjectNotFoundException;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class CustomValidatorTest {

    @InjectMocks
    private CustomValidator validator;

    @Mock
    private ConstraintValidatorContext context;

    @Test
    public void testPhoneNumberValidation() {
        CustomValidation annotation = mock(CustomValidation.class);
        when(annotation.type()).thenReturn(ValidationType.PHONE_NUMBER);

        validator.initialize(annotation);

        assertTrue(validator.isValid("+90 555 555 5555", context));
        assertFalse(validator.isValid("+90 1234567890", context));
    }

    @Test
    public void testPasswordValidation() {
        CustomValidation annotation = mock(CustomValidation.class);
        when(annotation.type()).thenReturn(ValidationType.PASSWORD);

        validator.initialize(annotation);

        assertTrue(validator.isValid("password", context));
        assertFalse(validator.isValid("pass", context));
    }

    @Test
    public void testNullValue() {
        CustomValidation annotation = mock(CustomValidation.class);
        when(annotation.type()).thenReturn(ValidationType.PHONE_NUMBER);

        validator.initialize(annotation);

        assertThrows(ObjectNotFoundException.class, () -> validator.isValid(null, context));
    }
}
