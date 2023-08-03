package com.example.server.exception;

import com.example.server.constant.ApiError;
import com.example.server.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    public ResponseEntity<ApiResponse<Void>> handleRequestBodyException() {
        ApiResponse<Void> response = new ApiResponse<>(ApiError.E440);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // FUNCTION 数据校验异常
    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<List<String>>> handleMethodArgumentNotValid(Exception e) {
        ApiResponse<List<String>> response = new ApiResponse<>(ApiError.E441);
        List<String> list = new LinkedList<>();

        if (e instanceof MethodArgumentNotValidException) {
            // BeanValidation exception
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            BindingResult result = ex.getBindingResult();

            result.getFieldErrors().forEach(error -> {
                String field = error.getField();
                Object value = error.getRejectedValue();
                String msg = error.getDefaultMessage();
                list.add(String.format("错误字段: %s - 错误值: %s - 原因: %s", field, value, msg));
            });
        }

        if (e instanceof ConstraintViolationException) {
            // BeanValidation GET simple param
            ConstraintViolationException ex = (ConstraintViolationException) e;

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            constraintViolations.forEach(violation -> {
                String field = violation.getPropertyPath().toString();
                Object value = violation.getInvalidValue();
                String msg = violation.getMessage();
                list.add(String.format("错误字段: %s - 错误值: %s - 原因: %s", field, value, msg));
            });
        }

        response.setData(list);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
