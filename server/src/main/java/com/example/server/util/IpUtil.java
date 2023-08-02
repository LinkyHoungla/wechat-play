package com.example.server.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

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
