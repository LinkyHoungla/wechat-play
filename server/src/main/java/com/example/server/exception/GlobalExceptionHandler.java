package com.example.server.exception;

import com.example.server.constant.ApiError;
import com.example.server.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // FUNCTION 全局Api异常处理
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ax) {
        ApiResponse<Void> response = new ApiResponse<>(ax.getApiError());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // FUNCTION 请求体参数缺失
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleRequestBodyException(HttpMessageNotReadableException he) {
        ApiResponse<Void> response = new ApiResponse<>(ApiError.E440);
        return ResponseEntity.status(response.getStatus()).body(response);
    }



}
