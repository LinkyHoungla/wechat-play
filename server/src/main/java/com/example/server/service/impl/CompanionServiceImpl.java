package com.example.server.service.impl;

import com.example.server.dao.CompanionDao;
import com.example.server.dto.param.GameParam;
import com.example.server.dto.param.ServiceParam;
import com.example.server.dto.param.USidParam;
import com.example.server.entity.Companion;
import com.example.server.entity.Game;
import com.example.server.service.CompanionService;
import com.example.server.util.PageQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanionServiceImpl implements CompanionService {
    private final CompanionDao companionDao;

    @Override
    public PageQuery<Companion> getCompanionList(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(companionDao.getCompanionList(query)));
    }

    @Override
    public Integer addCompanion(USidParam param) {
        return companionDao.addCompanion(param);
    }

    @Override
    public Integer updateStatus(Integer id, String status) {
        return companionDao.updateCompanion(id, status);
    }

    @Override
    public Integer deleteCompanion(Integer id) {
        return companionDao.deleteCompanion(id);
    }

    @Override
    public PageQuery<com.example.server.entity.Service> getServiceList(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(companionDao.getServiceList(query)));
    }

    @Override
    public Integer addService(ServiceParam param) {
        return companionDao.addService(param);
    }

    @Override
    public Integer updateService(ServiceParam param) {
        return companionDao.updateService(param);
    }

    @Override
    public Integer deleteService(Integer id) {
        return companionDao.deleteService(id);
    }

    @Override
    public PageQuery<Game> getGameList(String query, String tag, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        System.out.println(query);
        System.out.println(tag);
        return new PageQuery<>(new PageInfo<>(companionDao.getGameList(query, tag)));
    }

    @Override
    public Integer addGame(GameParam param) {
        return companionDao.addGame(param);
    }

    @Override
    public Integer updateGame(GameParam param) {
        return companionDao.updateGame(param);
    }

    @Override
    public Integer deleteGame(Integer id) {
        return companionDao.deleteGame(id);
    }


}
