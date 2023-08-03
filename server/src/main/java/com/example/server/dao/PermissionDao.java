package com.example.server.dao;

import com.example.server.dto.param.PermissionParam;
import com.example.server.dto.param.RoleParam;
import com.example.server.dto.vo.AuthTree;
import com.example.server.dto.vo.PermissionTree;
import com.example.server.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {
    // SECTION 权限角色
    // FUNCTION 分页查询
    List<Role> getRoleList(String query);
    // FUNCTION 添加
    @Insert("INSERT role(`name`, `desc`) VALUES (#{name}, #{desc})")
    Integer addRole(RoleParam param);
    // FUNCTION 修改
    @Update("UPDATE role SET `name` = #{name}, `desc` = #{desc} WHERE id = #{id}")
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
    @Select("SELECT p.id, p.pid, p.name FROM permission p WHERE pid = -1")
    List<AuthTree> getAuthParent();
    @Select("SELECT p.id, p.pid, p.name FROM permission p WHERE pid = #{pid}")
    List<AuthTree> getAuthChildren(Integer pid);
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
    // FUNCTION 清空
    @Update("DELETE FROM role_permission WHERE rid = #{id}")
    Integer removeAuth(Integer id);

}
