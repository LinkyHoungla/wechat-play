package com.example.server.service;

import com.example.server.dto.param.RequireParam;
import com.example.server.entity.Order;
import com.example.server.util.PageQuery;

public interface OrderService {
    // SECTION 订单管理
    // FUNCTION 分页查询
    PageQuery<Order> getOrderList(String query, Integer pageNum, Integer pageSize);
    // FUNCTION 添加
    Integer addOrder(RequireParam param);
    // FUNCTION 修改
    Integer updateStatus(String id, String status);

}
