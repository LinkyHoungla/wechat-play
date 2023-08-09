package com.example.server.service.impl;

import com.example.server.constant.ApiError;
import com.example.server.dao.AdminDao;
import com.example.server.dto.param.AdminInfoParam;
import com.example.server.dto.vo.AdminInfo;
import com.example.server.dto.vo.CurrentAdmin;
import com.example.server.dto.param.AdminParam;
import com.example.server.dto.vo.Menu;
import com.example.server.exception.ApiException;
import com.example.server.service.AdminService;
import com.example.server.util.FileUtil;
import com.example.server.util.JwtUtil;
import com.example.server.util.PageQuery;
import com.example.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;
    @Value("${file.save-path}")
    private String FILEPATH;
    private final static String SAVE = "/avatar/admin/";

    @Override
    @Transactional
    public String login(String username, String password, String ip) {
        Map<String, Object> map = adminDao.login(username, password);

        if( map == null || map.get("id") == null || map.get("rid") == null)
            throw new ApiException(ApiError.E451);

        Integer id = (Integer) map.get("id");
        Integer rid = (Integer) map.get("rid");

        if (adminDao.setIp(id, ip) != 1)
            throw new ApiException(ApiError.E460);

        return JwtUtil.generateToken(String.valueOf(id), ip, rid);
    }

    @Override
    public Boolean logout(String token) {
        return JwtUtil.revokedToken(token);
    }

    @Override
    @Transactional
    public CurrentAdmin getCurrentAdmin(Integer id, Integer rid) {
        CurrentAdmin ca =  adminDao.getBasic(id);
        ca.setPermissions(adminDao.getPermissions(rid));

        List<Menu> p = adminDao.getMenuParent(rid);
        for (Menu c : p) {
            c.setChildren(adminDao.getMenuChild(rid, c.getId()));
        }
        ca.setMenus(p);

        return ca;
    }

    @Override
    public PageQuery<AdminInfo> getAdminList(String query, String tag, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(adminDao.getAdminList(query, tag)));
    }

    @Override
    public Integer addAdmin(AdminParam param) {
        return adminDao.addAdmin(param);
    }

    @Override
    public Integer updateStatus(AdminParam param) {
        return adminDao.updateStatus(param);
    }

    @Override
    @Transactional
    public Integer updateAdmin(Integer id, AdminInfoParam param) {
        String name = param.getName();
        String username = param.getUsername();
        String password = param.getPassword();

        if (name == null && username == null && password == null)
            throw new ApiException(ApiError.E440);

        return adminDao.updateAdminName(name,id) + adminDao.updateAdminInfo(username,password,id);
    }

    @Override
    @Transactional
    public String uploadAvatar(Integer id, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null)
            throw new ApiException(ApiError.E462);

        // 旧文件删除
        String old = adminDao.getOldAvatar(id);
        FileUtil.deleteOldFile(FILEPATH + SAVE, old);

        // 新文件写入
        String filename = id + "-" + UuidUtil.generateUniqueId() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String result = FileUtil.uploadFile(FILEPATH + SAVE, filename, file);

        // 文件名保存
        adminDao.uploadAvatar(id, filename);

        return result;
    }

    @Override
    public Integer deleteAdmin(Integer id) {
        return adminDao.deleteAdmin(id);
    }
}
