package com.example.server.service.impl;

import com.example.server.dao.PermissionDao;
import com.example.server.dto.param.PermissionParam;
import com.example.server.dto.param.RoleParam;
import com.example.server.dto.vo.AuthTree;
import com.example.server.dto.vo.PermissionTree;
import com.example.server.entity.Role;
import com.example.server.service.PermissionService;
import com.example.server.util.PageQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public PageQuery<PermissionTree> getPermissionTree(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageQuery<PermissionTree> pageQuery = new PageQuery<>(new PageInfo<>(permissionDao.getPermissionParent(query)));

        for (PermissionTree parent : pageQuery.getList()) {
            List<PermissionTree> sons = permissionDao.getPermissionChildren(parent.getId());

            for (PermissionTree son : sons) {
                son.setChildren(permissionDao.getPermissionChildren(son.getId()));
            }

            parent.setChildren(sons);
        }

        return pageQuery;
    }

    @Override
    public List<AuthTree> getAuthTree(Integer level) {
        List<AuthTree> parents = permissionDao.getAuthParent();

        if (level > 1) {
            for (AuthTree parent : parents) {
                List<AuthTree> sons = permissionDao.getAuthChildren(parent.getId());

                if (level > 2) {
                    for (AuthTree son : sons) {
                        son.setChildren(permissionDao.getAuthChildren(son.getId()));
                    }
                }

                parent.setChildren(sons);
            }
        }

        return parents;
    }

    @Override
    public Integer addPermission(PermissionParam param) {
        return permissionDao.addPermission(param);
    }

    @Override
    public Integer updatePermission(PermissionParam param) {
        return permissionDao.updatePermission(param);
    }

    @Override
    public Integer deletePermission(Integer id) {
        return permissionDao.deletePermission(id);
    }

    @Override
    @Transactional
    public Integer auth(Integer id, List<Integer> auth) {
        return permissionDao.removeAuth(id) + permissionDao.addAuth(id, auth);
    }
}