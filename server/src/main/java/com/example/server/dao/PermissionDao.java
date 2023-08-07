package com.example.server.dao;

import com.example.server.dto.param.PermissionParam;
import com.example.server.dto.param.RoleParam;
import com.example.server.dto.vo.AuthTree;
import com.example.server.dto.vo.PermissionTree;
import com.example.server.dto.vo.RoleList;
import com.example.server.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {
    // SECTION 权限角色
    // FUNCTION 分页查询
    List<Role> getRoleList(String query);
    @Select("SELECT r.id, r.name FROM role r")
    List<RoleList> getRoles();
    // FUNCTION 添加
    @Insert("INSERT role(`name`, `desc`) VALUES (#{name}, #{desc})")
    Integer addRole(RoleParam param);
    // FUNCTION 修改
    @Update("UPDATE role SET `name` = #{param.name}, `desc` = #{param.desc} WHERE id = #{id}")
    Integer updateRole(Integer id, RoleParam param);
    // FUNCTION 删除
    @Delete("DELETE FROM role WHERE id = #{id}")
    Integer deleteRole(Integer id);

    // SECTION 权限
    // FUNCTION 分页查询权限树
    List<PermissionTree> getPermissionParent(String query);
    @Select("SELECT * FROM permission WHERE pid = #{pid}")
    List<PermissionTree> getPermissionChildren(Integer pid);
    // FUNCTION 获取授权树
    List<AuthTree> getAuthTree(Integer pid, Integer rid);
    // FUNCTION 获取角色权限列表
    @Select("SELECT pid FROM role_permission WHERE rid = #{rid}")
    List<Integer> getPermissions(Integer rid);
    List<Integer> getChildren(Integer rid, Integer pid);
    // FUNCTION 添加
    Integer addPermission(PermissionParam param);
    // FUNCTION 添加授权
    Integer addAuth(Integer id, List<Integer> auth);
    // FUNCTION 修改
    @Update("UPDATE permission SET `name` = #{name}, path = #{path}, icon = #{icon} WHERE id = #{id}")
    Integer updatePermission(PermissionParam param);
    // FUNCTION 删除
    @Delete("DELETE FROM permission WHERE id = #{id}")
    Integer deletePermission(Integer id);
    Integer deleteAuth(Integer rid, List<Integer> auth);
    // FUNCTION 清空
    @Update("DELETE FROM role_permission WHERE rid = #{id}")
    Integer removeAuth(Integer id);

}
