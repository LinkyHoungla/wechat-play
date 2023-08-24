package com.example.server.service;

import com.example.server.dto.param.PermissionParam;
import com.example.server.dto.param.RoleParam;
import com.example.server.dto.vo.AuthTree;
import com.example.server.dto.vo.PermissionTree;
import com.example.server.dto.vo.RoleList;
import com.example.server.entity.Role;
import com.example.server.util.PageQuery;

import java.util.List;

public interface PermissionService {
    // SECTION 权限角色
    // FUNCTION 分页查询
    PageQuery<Role> getRoleList(String query, Integer pageNum, Integer pageSize);
    // FUNCTION 列表
    List<RoleList> getRoles();
    // FUNCTION 添加
    Integer addRole(RoleParam param);
    // FUNCTION 信息修改
    Integer updateRole(RoleParam param);
    // FUNCTION 删除
    Integer deleteRole(Integer id);

    // SECTION 权限
    // FUNCTION 获取权限树
    PageQuery<PermissionTree> getPermissionTree(String query, Integer pageNum, Integer pageSize);
    // FUNCTION 获取授权树
    List<AuthTree> getAuthTree(Integer level, Integer id);
    // FUNCTION 获取权限列表
    List<Integer> getAuthList(Integer id);
    // FUNCTION 添加
    Integer addPermission(PermissionParam param);
    // FUNCTION 修改
    Integer updatePermission(PermissionParam param);
    // FUNCTION 删除
    Integer deletePermission(Integer id);
    // FUNCTION 删除授权
    Integer deleteAuth(Integer rid, Integer pid);
    // FUNCTION 授权
    Integer auth(Integer id, List<Integer> auth);
}
