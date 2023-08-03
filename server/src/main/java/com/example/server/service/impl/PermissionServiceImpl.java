package com.example.server.service.impl;

import com.example.server.dao.PermissionDao;
import com.example.server.dto.param.RoleParam;
import com.example.server.entity.Role;
import com.example.server.service.PermissionService;
import com.example.server.util.PageQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao permissionDao;

    @Override
    public PageQuery<Role> getRoleList(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(permissionDao.getRoleList(query)));
    }

    @Override
    public Integer addRole(RoleParam param) {
        return permissionDao.addRole(param);
    }

    @Override
    public Integer updateRole(Integer id, RoleParam param) {
        return permissionDao.updateRole(id, param);
    }

    @Override
    public Integer deleteRole(Integer id) {
        return permissionDao.deleteRole(id);
    }
}
