package com.example.server.service;

import com.example.server.dto.param.GameParam;
import com.example.server.dto.param.ServiceParam;
import com.example.server.dto.param.USidParam;
import com.example.server.entity.Companion;
import com.example.server.entity.Game;
import com.example.server.entity.Service;
import com.example.server.util.PageQuery;

public interface CompanionService {
    // SECTION 陪玩管理
    // FUNCTION 分页查询
    PageQuery<Companion> getCompanionList(String query, String tag, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addCompanion(USidParam param);
    // FUNCTION 修改
    Integer updateStatus(Integer id, String status);
    // FUNCTION 删除
    Integer deleteCompanion(Integer id);

    // SECTION 服务管理
    // FUNCTION 分页查询
    PageQuery<Service> getServiceList(String query, String tag, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addService(ServiceParam param);
    // FUNCTION 修改
    Integer updateService(ServiceParam param);
    // FUNCTION 删除
    Integer deleteService(Integer id);

    // SECTION 游戏管理
    // FUNCTION 分页查询
    PageQuery<Game> getGameList(String query, String tag, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addGame(GameParam param);
    // FUNCTION 修改
    Integer updateGame(GameParam param);
    // FUNCTION 删除
    Integer deleteGame(Integer id);

}
