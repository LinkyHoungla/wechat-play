package com.example.server.service.impl;

import com.example.server.constant.ApiError;
import com.example.server.dao.AdminDao;
import com.example.server.exception.ApiException;
import com.example.server.model.ApiResponse;
import com.example.server.service.AdminService;
import com.example.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;

    @Override
    @Transactional
    public String login(String username, String password, String ip) {
        Map<String, Object> map = adminDao.login(username, password);

        if( map == null || map.get("id") == null)
            throw new ApiException(ApiError.E451);

        Integer id = (Integer) map.get("id");
        Integer rid = (Integer) map.get("rid");

        if (adminDao.setIp(id, ip) != 1)
            throw new ApiException(ApiError.E460);

        return JwtUtil.generateToken(String.valueOf(id), ip, rid);
    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        String token = JwtUtil.extractTokenFromRequest(request);
        if (token == null)
            throw new ApiException(ApiError.E453);

        return JwtUtil.revokedToken(token);
    }
}
