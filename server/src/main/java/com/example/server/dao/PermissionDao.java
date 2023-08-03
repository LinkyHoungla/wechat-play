package com.example.server.dao;

import com.example.server.dto.param.RoleParam;
import com.example.server.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

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

}
