package com.example.server.util.validator;

import com.example.server.annotation.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, CharSequence> {

    private Class<? extends Enum<?>> enumClass;
    private boolean ableNull;

    @Override
    public void initialize(EnumValue annotation) {
        enumClass = annotation.enumClass();
        ableNull = annotation.ableNull();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null values are considered valid
        }

        String stringValue = value.toString();
        Enum<?>[] enumValues = enumClass.getEnumConstants();

        // 允许空
        if (stringValue.trim().isEmpty() && ableNull)
            return true;

        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equals(stringValue)) {
                return true;
            }
        }

        return false;
    }

}
