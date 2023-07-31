package com.example.server.interceptor;

import com.example.server.constant.ApiError;
import com.example.server.exception.ApiException;
import com.example.server.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    // FUNCTION 登录校验拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            // 从请求头获取 jwt
            String jwt = JwtUtil.extractTokenFromRequest(request);
            if (jwt == null || JwtUtil.isTokenRevoked(jwt)) throw new ApiException(ApiError.E450);

            // 解析和验证 JWT
            Claims claims = JwtUtil.getClaimsByToken(jwt);

            // 在解析后的 Claims 中获取用户信息
            String ip = claims.getSubject();
            String id = claims.get("id", String.class);
            Integer rid = claims.get("rid", Integer.class);

            request.setAttribute("ip", ip);
            request.setAttribute("id", id);
            request.setAttribute("rid", rid);

        } catch (ExpiredJwtException e) {
            throw new ApiException(ApiError.E452);
        } catch (MalformedJwtException | SignatureException e) {
            throw new ApiException(ApiError.E453);
        }

        // 校验通过，允许请求继续访问后续拦截器
        return true;
    }
}
