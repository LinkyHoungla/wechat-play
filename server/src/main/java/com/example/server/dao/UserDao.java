package com.example.server.dao;

import com.example.server.dto.param.UserInfoParam;
import com.example.server.dto.param.UserLogParam;
import com.example.server.dto.vo.UserInfoVo;
import com.example.server.dto.vo.UserMana;
import com.example.server.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    // SECTION 用户管理
    // FUNCTION 获取
    List<UserMana> getUserList(String query);
    // FUNCTION 信息获取
    @Select("SELECT * FROM user_info WHERE id = #{id}")
    UserInfo getUserInfoMana(String id);
    // FUNCTION 添加
    @Insert("INSERT INTO user (id, phone, wxid) VALUES (#{id}, #{param.phone}, #{param.wxid})")
    Integer addUser(String id, UserLogParam param);
    // FUNCTION 修改
    @Update("UPDATE user SET status = #{status} WHERE id = #{id}")
    Integer updateStatus(String id, String status);
    // FUNCTION 删除
    @Update("UPDATE user SET status = 'DELETED' WHERE id = #{id}")
    Integer deleteUser(String id);


    // SECTION 用户操作
    // FUNCTION 登录
    Map<String, Object> login(String username, String password);
    @Update("UPDATE user_login SET ip = #{ip} WHERE id = #{id}")
    Integer setIp(String id, String ip);
    // FUNCTION 信息获取
    @Select("SELECT * FROM user_info WHERE id = #{id}")
    UserInfoVo getUserInfo(String id);
    // FUNCTION 信息修改
    @Update("UPDATE user_info " +
            "SET name = #{name}, gender = #{gender}, age = #{age}, birth = #{birth}, location = #{location} " +
            "WHERE id = #{id}")
    Integer updateUser(UserInfoParam param);
}
