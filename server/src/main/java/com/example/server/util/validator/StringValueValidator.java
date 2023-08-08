package com.example.server.util.validator;

import com.example.server.annotation.StringValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringValueValidator implements ConstraintValidator<StringValue, String> {
    private String regex;
    private int min;
    private int max;

    @Override
    public void initialize(StringValue annotation) {
        this.regex = annotation.regex();
        this.min = annotation.min();
        this.max = annotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // 允许为 null
        }

        // 校验字符串不为空
        if (value.trim().isEmpty()) {
            return false;
        }

        // 校验字符串匹配正则表达式
        if (!value.matches(regex)) {
            return false;
        }

        // 校验字符串长度
        int length = value.length();
        return length >= min && length <= max;
    }

}
