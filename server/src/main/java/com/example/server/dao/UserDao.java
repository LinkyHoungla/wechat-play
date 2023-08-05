package com.example.server.dao;

import com.example.server.dto.param.UserInfoParam;
import com.example.server.dto.param.UserLogParam;
import com.example.server.dto.vo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    // SECTION 用户管理
    // FUNCTION 获取
    List<UserInfo> getUserList(String query);
    // FUNCTION 添加
    @Insert("INSERT INTO user (id, nickname, phone, wxid) VALUES (#{id}, #{param.nickname}, #{param.phone}, #{param.wxid})")
    Integer addUser(String id, UserLogParam param);
    // FUNCTION 修改
    @Update("UPDATE user " +
            "SET nickname = #{nickname}, gender = #{gender}, age = #{age}, birth = #{birth}, location = #{location} " +
            "WHERE id = #{id}")
    Integer updateUser(UserInfoParam param);
    // FUNCTION 删除
    @Update("UPDATE user SET status = 'DELETED' WHERE id = #{id}")
    Integer deleteUser(String id);


    // SECTION 用户操作
    // FUNCTION 登录
    Map<String, Object> login(String username, String password);
    @Update("UPDATE user_login SET ip = #{ip} WHERE id = #{id}")
    Integer setIp(String id, String ip);
}
