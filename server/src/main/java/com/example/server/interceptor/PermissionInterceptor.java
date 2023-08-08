package com.example.server.interceptor;

import com.example.server.annotation.RequirePermission;
import com.example.server.constant.ApiError;
import com.example.server.dao.PermissionDao;
import com.example.server.exception.ApiException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor {
    public PermissionDao dao;

    public PermissionInterceptor(PermissionDao dao) {
        this.dao = dao;
    }

    // FUNCTION 权限校验拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequirePermission annotation = handlerMethod.getMethodAnnotation(RequirePermission.class);
            if (annotation != null) {
                // 获取注解中指定的权限ID
                int pid = annotation.pid();
                // 获取token中的角色ID
                int rid = (Integer) request.getAttribute("rid");

                if (dao.hasPermissions(rid, pid) == null)
                    throw new ApiException(ApiError.E403);
            }
        }
        return true; // 校验通过，继续执行请求方法
    }
}
