/*
package com.assigndevelopers.airportluggagehandlingapi.dto;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;


@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {
    Class<? extends Enum<?>> enumClass();
    String message() default "Value is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private String[] acceptedValues;

    @Override
    public void initialize(EnumValidator annotation) {
        acceptedValues = Arrays.stream(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toArray(String[]::new);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // handle @NotNull separately
        return Arrays.asList(acceptedValues).contains(value);
    }
}*/
