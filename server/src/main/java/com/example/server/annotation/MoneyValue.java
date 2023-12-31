package com.example.server.annotation;

import com.example.server.util.validator.EnumValueValidator;
import com.example.server.util.validator.MoneyValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.math.BigDecimal;

@Constraint(validatedBy = MoneyValueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MoneyValue {
    String message() default "金额非法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long max() default 10000L;
}
