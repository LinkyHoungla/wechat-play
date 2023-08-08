package com.example.server.service.impl;

import com.example.server.constant.ApiError;
import com.example.server.dao.OrderDao;
import com.example.server.dto.param.BalanceParam;
import com.example.server.dto.param.RequireParam;
import com.example.server.entity.Order;
import com.example.server.entity.Wallet;
import com.example.server.exception.ApiException;
import com.example.server.service.OrderService;
import com.example.server.util.PageQuery;
import com.example.server.util.UuidUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Override
    public PageQuery<Order> getOrderList(String query, String tag, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(orderDao.getOrderList(query, tag)));
    }

    @Override
    public Integer addOrder(RequireParam param) {
        ObjectMapper ob = new ObjectMapper();

        String voice;
        String nature;
        try {
            voice = ob.writeValueAsString(param.getVoice());
            nature = ob.writeValueAsString(param.getNature());
        } catch (JsonProcessingException e) {
            throw new ApiException(ApiError.E460);
        }

        int time = 0;
        while (true) {
            String id =  UuidUtil.generateUniqueId();
            try {
                int len = orderDao.addOrder(id, voice, nature, param);
                if (len > 0) {
                    return len; // 插入成功，返回插入的行数
                }
            } catch (DuplicateKeyException e) {
                // 插入失败，继续循环生成新的 UID
                if (time ++ > 20)
                    throw new ApiException(ApiError.E461);
            }
        }
    }

    @Override
    public Integer updateStatus(String id, String status) {
        return orderDao.updateOrder(id, status);
    }

    @Override
    public PageQuery<Wallet> getWalletList(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageQuery<>(new PageInfo<>(orderDao.getWalletList(query)));
    }

    @Override
    public Integer updateWallet(BalanceParam param) {
        return orderDao.updateBalance(param);
    }

}
