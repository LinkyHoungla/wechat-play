package com.example.server.service.impl;

import com.example.server.constant.ApiError;
import com.example.server.dao.UserDao;
import com.example.server.dto.param.StatusParam;
import com.example.server.dto.param.UserInfoParam;
import com.example.server.dto.param.UserLogParam;
import com.example.server.dto.vo.UserInfoVo;
import com.example.server.dto.vo.UserMana;
import com.example.server.entity.UserInfo;
import com.example.server.exception.ApiException;
import com.example.server.service.UserService;
import com.example.server.util.FileUtil;
import com.example.server.util.JwtUtil;
import com.example.server.util.PageQuery;
import com.example.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Value("${file.save-path}")
    private String FILEPATH;
    private final static String SAVE = "/avatar/user/";

    @Override
    @Transactional
    public String login(String username, String password, String ip) {
        Map<String, Object> map = userDao.login(username, password);

        if( map == null || map.get("id") == null)
            throw new ApiException(ApiError.E451);

        String id = (String) map.get("id");
        Integer rid = (Integer) map.get("rid");

        if (userDao.setIp(id, ip) != 1)
            throw new ApiException(ApiError.E460);

        return JwtUtil.generateToken(id, ip, rid);
    }

    @Override
    public Boolean logout(String token) {
        return JwtUtil.revokedToken(token);
    }

    @Override
    public UserInfoVo getUserInfo(String id) {
        return userDao.getUserInfo(id);
    }

    @Override
    public Integer updateUserInfo(UserInfoParam param) {
        return userDao.updateUser(param);
    }

    @Override
    @Transactional
    public String updateAvatar(String id, MultipartFile file) {
        // 旧文件删除
        String old = userDao.getOldAvatar(id);

        // 新文件写入
        String result = FileUtil.uploadFile(old, FILEPATH + SAVE, id, file);

        // 文件名保存
        userDao.uploadAvatar(id, result);

        return result;
    }

    @Override
    public PageQuery<UserMana> getUserList(String query, String tag, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(userDao.getUserList(query, tag)));
    }

    @Override
    public UserInfo getUserInfoMana(String id) {
        return userDao.getUserInfoMana(id);
    }

    @Override
    public Integer addUser(UserLogParam param) {
        while (true) {
            String id =  UuidUtil.generateUniqueId();
            try {
                int len = userDao.addUser(id, param);
                if (len > 0) {
                    return len; // 插入成功，返回插入的行数
                }
            } catch (DuplicateKeyException e) {
                // 插入失败，继续循环生成新的 UID
            }
        }
    }

    @Override
    public Integer updateStatus(StatusParam param) {
        return userDao.updateStatus(param.getId(), param.getStatus());
    }

    @Override
    public Integer deleteUser(String id) {
        return userDao.deleteUser(id);
    }
}
