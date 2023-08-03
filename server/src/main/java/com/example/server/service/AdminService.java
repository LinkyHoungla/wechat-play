package com.example.server.service;

import com.example.server.dto.vo.AdminInfo;
import com.example.server.dto.vo.CurrentAdmin;
import com.example.server.dto.param.AdminParam;
import com.example.server.util.PageQuery;

public interface AdminService {
    // SECTION 管理员登录
    // FUNCTION 登录
    String login(String username, String password, String ip);
    // FUNCTION 登出
    // TODO: 2023/7/31 登出由Redis完成，构建id和Token的键值对保持，logout会经过拦截，故为有效token，这需要进入
    Boolean logout(String token);
    // FUNCTION 登录信息
    CurrentAdmin getCurrentAdmin(Integer id, Integer rid);

    // SECTION 管理员管理
    // FUNCTION 分页查询
    PageQuery<AdminInfo> getAdminList(String query, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addAdmin(AdminParam param);
    // FUNCTION 信息修改
    Integer updateAdmin(Integer id, AdminParam param);
    // FUNCTION 删除
    Integer deleteAdmin(Integer id);
}
