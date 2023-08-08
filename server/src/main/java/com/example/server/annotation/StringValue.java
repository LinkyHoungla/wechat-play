package com.example.server.annotation;

import com.example.server.util.validator.StringValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StringValueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringValue {
    String message() default "字段非法";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String regex() default ".*";
    int max() default 200;
    int min() default 0;
}
