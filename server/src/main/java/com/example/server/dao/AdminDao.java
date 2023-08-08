package com.example.server.dao;

import com.example.server.dto.vo.AdminInfo;
import com.example.server.dto.param.AdminParam;
import com.example.server.dto.vo.CurrentAdmin;
import com.example.server.dto.vo.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDao {
    // SECTION 管理员 登录
    // FUNCTION 登录
    Map<String, Object> login(String username, String password);
    @Update("UPDATE admin_login SET ip = #{ip} WHERE id = #{id}")
    Integer setIp(Integer id, String ip);

    // SECTION 管理员信息
    // FUNCTION 基本信息
    CurrentAdmin getBasic(Integer id);
    @Select("SELECT pid FROM role_permission WHERE rid = #{rid}")
    List<Integer> getPermissions(Integer rid);
    @Select("SELECT p.id, p.name, p.path, p.icon " +
            "FROM permission p " +
            "JOIN role_permission rp ON p.id = rp.pid " +
            "WHERE rp.rid = #{id} AND p.pid = -1")
    List<Menu> getMenuParent(Integer rid);
    @Select("SELECT p.id, p.name, p.path " +
            "FROM permission p " +
            "JOIN role_permission rp ON p.id = rp.pid " +
            "WHERE rp.rid = #{rid} AND p.pid = #{pid}")
    List<Menu> getMenuChild(Integer rid, Integer pid);
    // FUNCTION 信息修改
    @Update("UPDATE admin SET name = #{name} WHERE id = #{id}")
    Integer updateAdminName(String name, Integer id);
    Integer updateAdminInfo(String username, String password, Integer id);
    // FUNCTION 头像上传
    @Update("UPDATE admin SET avatar = #{url} WHERE id = #{id}")
    Integer uploadAvatar(Integer id, String url);

    // SECTION 管理员管理
    // FUNCTION 分页查询
    List<AdminInfo> getAdminList(String query, String tag);
    // FUNCTION 添加
    @Insert("INSERT INTO admin(name, rid)  VALUES (#{name}, #{rid})")
    Integer addAdmin(AdminParam param);
    // FUNCTION 状态管理
    @Update("UPDATE admin SET status = #{status}, rid = #{rid} WHERE id = #{id}")
    Integer updateStatus(AdminParam param);
    // FUNCTION 删除
    @Update("UPDATE admin SET status = 'DELETED' WHERE id = #{id}")
    Integer deleteAdmin(Integer id);
}
