package com.example.server.dao;

import com.example.server.dto.param.RequireParam;
import com.example.server.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderDao {
    // SECTION 陪娃管理
    // FUNCTION 获取
    List<Order> getOrderList(String query);
    // FUNCTION 添加
    @Insert("INSERT INTO " +
            "`order_requirement` (id, uid, sid, serve_type, gender, voice, nature, note) " +
            "VALUES " +
            "(#{id}, #{param.uid}, #{param.sid}, #{param.serveType}, #{param.gender}, #{voice}, #{nature}, #{param.note})")
    Integer addOrder(String id, String voice, String nature,RequireParam param);
    // FUNCTION 修改
    @Update("UPDATE `order` SET status = #{status} WHERE id = #{id} ")
    Integer updateOrder(String id, String status);


}
