package com.example.server.service.impl;

import com.example.server.constant.ApiError;
import com.example.server.dao.StoreDao;
import com.example.server.dto.param.BalanceParam;
import com.example.server.dto.param.StoreParam;
import com.example.server.entity.Balance;
import com.example.server.entity.Store;
import com.example.server.exception.ApiException;
import com.example.server.service.StoreService;
import com.example.server.util.PageQuery;
import com.example.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreDao storeDao;

    @Override
    public PageQuery<Store> getStoreList(String query, String tag, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(storeDao.getStoreList(query, tag)));
    }

    @Override
    public Integer addStore(StoreParam param) {
        int times = 0;
        while (true) {
            String id =  UuidUtil.generateUniqueId();
            try {
                int len = storeDao.addStore(id, param);
                if (len > 0) {
                    return len; // 插入成功，返回插入的行数
                }
            } catch (DuplicateKeyException e) {
                // 插入失败，继续循环生成新的 UID
                if (times++ >= 10)
                    throw new ApiException(ApiError.E461);
            }
        }
    }

    @Override
    public Integer updateStore(StoreParam param) {
        return storeDao.updateStore(param);
    }

    @Override
    public Integer deleteStore(String id) {
        return storeDao.deleteStore(id);
    }

    @Override
    public PageQuery<Balance> getBalanceList(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(storeDao.getBalanceList(query)));
    }

    @Override
    public Integer addBalance(BalanceParam param) {
        return storeDao.addBalance(param);
    }

    @Override
    public Integer updateBalance(BalanceParam param) {
        return storeDao.updateBalance(param);
    }
}
