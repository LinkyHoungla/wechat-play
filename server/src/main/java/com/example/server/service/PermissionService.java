package com.example.server.service;

import com.example.server.dto.param.RoleParam;
import com.example.server.entity.Role;
import com.example.server.util.PageQuery;

import java.util.Map;

public interface PermissionService {
    // SECTION 权限角色
    // FUNCTION 分页查询
    PageQuery<Role> getRoleList(String query, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addRole(RoleParam param);
    // FUNCTION 信息修改
    Integer updateRole(Integer id, RoleParam param);
    // FUNCTION 删除
    Integer deleteRole(Integer id);
}
