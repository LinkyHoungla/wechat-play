package com.example.server.service.impl;

import com.example.server.dao.CompanionDao;
import com.example.server.dto.param.CompanionParam;
import com.example.server.entity.Companion;
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
    public Integer addCompanion(CompanionParam param) {
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
}
