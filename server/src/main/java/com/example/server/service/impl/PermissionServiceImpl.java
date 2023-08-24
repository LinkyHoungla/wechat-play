package com.example.server.service.impl;

import com.example.server.dao.PermissionDao;
import com.example.server.dto.param.PermissionParam;
import com.example.server.dto.param.RoleParam;
import com.example.server.dto.vo.AuthTree;
import com.example.server.dto.vo.PermissionTree;
import com.example.server.dto.vo.RoleList;
import com.example.server.entity.Role;
import com.example.server.service.PermissionService;
import com.example.server.util.PageQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<RoleList> getRoles() {
        return permissionDao.getRoles();
    }

    @Override
    public Integer addRole(RoleParam param) {
        return permissionDao.addRole(param);
    }

    @Override
    public Integer updateRole(RoleParam param) {
        return permissionDao.updateRole(param);
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
    public List<AuthTree> getAuthTree(Integer level, Integer id) {
        List<AuthTree> parents = permissionDao.getAuthTree(-1, id);

        if (level > 1) {
            for (AuthTree parent : parents) {
                List<AuthTree> sons = permissionDao.getAuthTree(parent.getId(), id);

                if (level > 2) {
                    for (AuthTree son : sons) {
                        son.setChildren(permissionDao.getAuthTree(son.getId(), id));
                    }
                }

                parent.setChildren(sons);
            }
        }

        return parents;
    }

    @Override
    public List<Integer> getAuthList(Integer id) {
        return permissionDao.getPermissions(id);
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
    public Integer deleteAuth(Integer rid, Integer pid) {
        List<Integer> list = new ArrayList<>();

        list.add(pid);
        for (int i = 0; i < list.size(); i ++) {
            list.addAll(permissionDao.getChildren(rid, list.get(i)));
        }

        return permissionDao.deleteAuth(rid, list);
    }

    @Override
    @Transactional
    public Integer auth(Integer id, List<Integer> auth) {
        return permissionDao.removeAuth(id) + permissionDao.addAuth(id, auth);
    }
}
