package com.example.server.util;

import javax.servlet.http.HttpServletRequest;

public class ValidateUtil {

    // 手机正则
    public static final String PHONE = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
    // 用户ID正则
    public static final String UID = "\\d{10}";
    // ID正则
    public static final String ID = "^[0-9]$";
    // 订单ID正则
    public static final String OID = "\\d{20}";
    // 特殊字符校验
    public static final String USERNAME = "^[a-zA-Z0-9_]*$";
    // 只能中文字符
    public static final String CHINESE = "^[\\u4e00-\\u9fa5]{0,}$";
    // 用户昵称
    public static final String NICKNAME = "^[A-Za-z0-9_\\u4e00-\\u9fa5]+$";

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

}
