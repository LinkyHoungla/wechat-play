package com.example.server.service;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    // SECTION 管理员登录
    // FUNCTION 登录
    String login(String username, String password, String ip);
    // FUNCTION 登出
    // TODO: 2023/7/31 登出由Redis完成，构建id和Token的键值对保持，logout会经过拦截，故为有效token，这需要进入 
    Boolean logout(HttpServletRequest request);
}
