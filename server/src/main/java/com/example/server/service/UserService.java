package com.example.server.service;

import com.example.server.dto.param.StatusParam;
import com.example.server.dto.param.UserInfoParam;
import com.example.server.dto.param.UserLogParam;
import com.example.server.dto.vo.UserInfoVo;
import com.example.server.dto.vo.UserMana;
import com.example.server.entity.UserInfo;
import com.example.server.util.PageQuery;

public interface UserService {
    // SECTION 用户管理
    // FUNCTION 分页查询
    PageQuery<UserMana> getUserList(String query, String tag,Integer pageNum, Integer pageSize);
    // FUNCTION 信息获取
    UserInfo getUserInfoMana(String id);
    // FUNCTION 添加
    Integer addUser(UserLogParam param);
    // FUNCTION 修改
    Integer updateStatus(StatusParam param);
    // FUNCTION 删除
    Integer deleteUser(String id);

    // SECTION 用户操作
    // FUNCTION 登录
    String login(String username, String password, String ip);
    // FUNCTION 登出
    Boolean logout(String token);
    // FUNCTION 查询
    UserInfoVo getUserInfo(String id);
    // FUNCTION 信息修改
    Integer updateUserInfo(UserInfoParam param);
}
