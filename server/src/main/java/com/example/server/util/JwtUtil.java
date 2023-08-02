package com.example.server.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUtil {
    // 过期时间
    private static final long EXPIRE = 60 * 60 * 24;
    // 32位密钥
    private static final String SECRET = "mYikM9MeRGtSsSDwFqtZqtWcRY2JYdw9";
    // token前缀
    private static final String TOKEN_PREFIX = "Bearer ";
    // 请求头参数名
    private static final String HEADER_STRING = "Authorization";
    // JWT黑名单
    // todo：后续使用redis完成对应功能，目前只是简单测试
    private static final List<String> revokedTokens = new ArrayList<>();

    // 生成Token
    public static String generateToken(String id, String ip, Integer rid) {
        // 设置有效期
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * EXPIRE);

        Claims claims = Jwts.claims().setSubject(ip);
        claims.put("id", id);
        claims.put("rid", rid);

        return TOKEN_PREFIX + Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    // FUNCTION 解析token
    public static Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    // FUNCTION 解析请求头token
    public static String extractTokenFromRequest(HttpServletRequest request) {
        // 从请求头或请求参数中获取令牌
        String token = request.getHeader(HEADER_STRING);
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    // FUNCTION 将令牌添加至失效名单
    public static Boolean revokedToken(String token) {
        return revokedTokens.add(token);
    }

    // FUNCTION 检查令牌是否在撤销令牌列表中
    public static Boolean isTokenRevoked(String token) {
        return revokedTokens.contains(token);
    }
}
