package com.example.server.service.impl;

import com.example.server.constant.ApiError;
import com.example.server.dao.AdminDao;
import com.example.server.dto.CurrentAdmin;
import com.example.server.entity.vo.Menu;
import com.example.server.exception.ApiException;
import com.example.server.model.ApiResponse;
import com.example.server.service.AdminService;
import com.example.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;

    @Override
    @Transactional
    public String login(String username, String password, String ip) {
        Map<String, Object> map = adminDao.login(username, password);

        if( map == null && map.get("id") == null)
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

    @Override
    @Transactional
    public CurrentAdmin getCurrentAdmin(Integer id, Integer rid) {
        Map<String, String> map =  adminDao.getBasic(id);

        if(map == null)
            throw new ApiException(ApiError.E460);

        CurrentAdmin ca = new CurrentAdmin(map.get("name"), map.get("role"));
        ca.setAvatar(map.get("avatar"));

        List<Menu> p = adminDao.getMenuParent(rid);
        for (Menu c : p) {
            System.out.println(c);
            c.setChildren(adminDao.getMenuChild(rid, c.getId()));
        }
        ca.setMenus(p);

        ca.setPermissions(adminDao.getPermissions(rid));

        return ca;
    }
}
