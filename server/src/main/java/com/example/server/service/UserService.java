package com.example.server.service;

import com.example.server.dto.param.UserInfoParam;
import com.example.server.dto.param.UserLogParam;
import com.example.server.dto.vo.UserInfo;
import com.example.server.util.PageQuery;

public interface UserService {
    // SECTION 用户管理
    // FUNCTION 分页查询
    PageQuery<UserInfo> getUserList(String query, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addUser(UserLogParam param);
    // FUNCTION 修改
    Integer updateUser(UserInfoParam param);
    // FUNCTION 删除
    Integer deleteUser(String id);

    // SECTION 用户操作
    // FUNCTION 登录
    String login(String username, String password, String ip);
    // FUNCTION 登出
    Boolean logout(String token);
}
