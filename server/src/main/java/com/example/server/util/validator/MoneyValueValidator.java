package com.example.server.util.validator;

import com.example.server.annotation.MoneyValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MoneyValueValidator implements ConstraintValidator<MoneyValue, BigDecimal> {

    private Class<? extends Enum<?>> enumClass;

    private int scale;
    private long max;

    @Override
    public void initialize(MoneyValue annotation) {
        this.max = annotation.max();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // 小数不大于2
        if (value.scale() > 2) {
            return false;
        }

        // 绝对值受限
        BigDecimal maxValue = BigDecimal.valueOf(max);
        if (value.abs().compareTo(maxValue) > 0) {
            return false;
        }

        return true;
    }

}
