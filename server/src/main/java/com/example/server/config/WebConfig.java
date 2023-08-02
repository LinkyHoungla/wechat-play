package com.example.server.config;

import com.example.server.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // FUNCTION 拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")                 // 需要拦截的路径
                .excludePathPatterns("/*/login")        // 不需要拦截的路径
                .excludePathPatterns("/error");
    }
}
