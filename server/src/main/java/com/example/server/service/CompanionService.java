package com.example.server.service;

import com.example.server.dto.param.USidParam;
import com.example.server.entity.Companion;
import com.example.server.util.PageQuery;

public interface CompanionService {
    // SECTION 陪玩管理
    // FUNCTION 分页查询
    PageQuery<Companion> getCompanionList(String query, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addCompanion(USidParam param);
    // FUNCTION 修改
    Integer updateStatus(Integer id, String status);
    // FUNCTION 删除
    Integer deleteCompanion(Integer id);

}
