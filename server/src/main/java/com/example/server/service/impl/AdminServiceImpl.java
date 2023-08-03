package com.example.server.service.impl;

import com.example.server.constant.ApiError;
import com.example.server.dao.AdminDao;
import com.example.server.dto.vo.AdminInfo;
import com.example.server.dto.vo.CurrentAdmin;
import com.example.server.dto.param.AdminParam;
import com.example.server.dto.vo.Menu;
import com.example.server.exception.ApiException;
import com.example.server.service.AdminService;
import com.example.server.util.JwtUtil;
import com.example.server.util.PageQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if( map == null || map.get("id") == null)
            throw new ApiException(ApiError.E451);

        Integer id = (Integer) map.get("id");
        Integer rid = (Integer) map.get("rid");

        if (adminDao.setIp(id, ip) != 1)
            throw new ApiException(ApiError.E460);

        return JwtUtil.generateToken(String.valueOf(id), ip, rid);
    }

    @Override
    public Boolean logout(String token) {
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

    @Override
    public PageQuery<AdminInfo> getAdminList(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(adminDao.getAdminList(query)));
    }

    @Override
    public Integer addAdmin(AdminParam param) {
        return adminDao.addAdmin(param);
    }

    @Override
    public Integer updateAdmin(Integer id, AdminParam param) {
        return adminDao.updateAdmin(id, param);
    }

    @Override
    public Integer deleteAdmin(Integer id) {
        return adminDao.deleteAdmin(id);
    }
}
