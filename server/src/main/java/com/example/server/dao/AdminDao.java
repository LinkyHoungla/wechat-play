package com.example.server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface AdminDao {
    // SECTION 管理员 登录

    // FUNCTION 登录
    Map<String, Object> login(String username, String password);
    @Update("UPDATE admin_login SET ip = #{ip} WHERE id = #{id}")
    Integer setIp(Integer id, String ip);
}
