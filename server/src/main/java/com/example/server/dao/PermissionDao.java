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
    // FUNCTION 权限验证
    @Select("SELECT rp.* FROM role_permission rp INNER JOIN permission p ON p.id = rp.pid WHERE p.path = #{path} AND rp.rid = #{rid}")
    Integer hasPermissions(Integer rid, String path);

    // SECTION 权限角色
    // FUNCTION 分页查询
    List<Role> getRoleList(String query);
    @Select("SELECT r.id, r.name FROM role r")
    List<RoleList> getRoles();
    // FUNCTION 添加
    @Insert("INSERT role(`name`, `desc`) VALUES (#{name}, #{desc})")
    Integer addRole(RoleParam param);
    // FUNCTION 修改
    @Update("UPDATE role SET `name` = #{name}, `desc` = #{desc} WHERE id = #{id}")
    Integer updateRole(RoleParam param);
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
    // @Select("SELECT p.id FROM permission p INNER JOIN role_permission rp ON rp.pid = p.id WHERE rp.rid = #{rid} AND p.`level` = 3")
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
