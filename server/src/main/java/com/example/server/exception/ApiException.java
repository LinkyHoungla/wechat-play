package com.example.server.exception;

import com.example.server.constant.ApiError;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {
    private ApiError apiError;

    public ApiException(ApiError apiError) {
        this.apiError = apiError;
    }
}
