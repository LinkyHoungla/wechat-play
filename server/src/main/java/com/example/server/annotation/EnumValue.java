package com.example.server.annotation;

import com.example.server.util.validator.EnumValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = EnumValueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(EnumValues.class)
public @interface EnumValue {
    String message() default "参数非法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

    boolean ableNull() default false;
}
