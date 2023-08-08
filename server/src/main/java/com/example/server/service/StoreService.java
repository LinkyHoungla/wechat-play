package com.example.server.service;


import com.example.server.dto.param.BalanceParam;
import com.example.server.dto.param.StoreParam;
import com.example.server.entity.Balance;
import com.example.server.entity.Store;
import com.example.server.util.PageQuery;

public interface StoreService {
    // SECTION 店铺管理
    // FUNCTION 分页查询
    PageQuery<Store> getStoreList(String query, String tag, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addStore(StoreParam param);
    // FUNCTION 修改
    Integer updateStore(StoreParam param);
    // FUNCTION 删除
    Integer deleteStore(String id);

    // SECTION 预存管理
    // FUNCTION 分页查询
    PageQuery<Balance> getBalanceList(String query, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addBalance(BalanceParam param);
    // FUNCTION 修改
    Integer updateBalance(BalanceParam param);
}
