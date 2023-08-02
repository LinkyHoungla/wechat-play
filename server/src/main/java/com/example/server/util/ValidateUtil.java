package com.example.server.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ValidateUtil {

    // FUNCTION IP地址获取
    public static String getClientIpAddress(HttpServletRequest request) {
        // 优先获取 X-Forwarded-For 请求头
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader != null && !xForwardedForHeader.isEmpty()) {
            // X-Forwarded-For 请求头可能包含多个 IP 地址，使用逗号分隔，取第一个 IP 地址
            String[] ipAddresses = xForwardedForHeader.split(",");
            return ipAddresses[0].trim();
        }

        // 若 X-Forwarded-For 请求头为空，则获取 X-Real-IP 请求头
        String xRealIpHeader = request.getHeader("X-Real-IP");
        if (xRealIpHeader != null && !xRealIpHeader.isEmpty()) {
            return xRealIpHeader;
        }

        // 若没有 X-Forwarded-For 和 X-Real-IP 请求头，则直接从请求中获取客户端 IP 地址
        return request.getRemoteAddr();
    }

    // FUNCTION 长度校验
    public static Boolean length(int min, int max, String str) {
        return str.length() > min && str.length() < max;
    }
    // FUNCTION 范围校验
    public static Boolean rangeInt(int min, int max, int num) {
        return num > min && num < max;
    }
    public static Boolean rangeFloat(float min, float max, float num) {
        return num > min && num < max;
    }
    // FUNCTION 手机号校验
    public static Boolean phone(String phone) {
        String pattern = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
        return Pattern.matches(pattern, phone);
    }
    // FUNCTION 邮箱校验
    public static Boolean email(String email) {
        String pattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.matches(pattern, email);
    }

}
