package com.example.server.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, CharSequence> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValue annotation) {
        enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null values are considered valid
        }

        String stringValue = value.toString();
        Enum<?>[] enumValues = enumClass.getEnumConstants();

        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equals(stringValue)) {
                return true;
            }
        }

        return false;
    }

}
