package com.example.server.dao;

import com.example.server.dto.param.BalanceParam;
import com.example.server.dto.param.StoreParam;
import com.example.server.entity.Balance;
import com.example.server.entity.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StoreDao {
    // SECTION 店铺管理
    // FUNCTION 获取
    List<Store> getStoreList(String query);
    // FUNCTION 添加
    @Insert("INSERT INTO store (id, oid, phone, name, `desc`, owner) VALUES (#{id}, #{param.oid}, #{param.phone}, #{param.name}, #{param.desc}, #{param.owner})")
    Integer addStore(String id, StoreParam param);
    // FUNCTION 修改
    @Update("UPDATE store " +
            "SET name = #{name}, `desc` = #{desc} " +
            "WHERE id = #{id}")
    Integer updateStore(StoreParam param);
    // FUNCTION 删除
    @Update("UPDATE store SET status = 'DELETED' WHERE id = #{id}")
    Integer deleteStore(String id);


    // SECTION 预存管理
    // FUNCTION 获取
    List<Balance> getBalanceList(String query);
    // FUNCTION 添加
    @Insert("INSERT INTO balance (uid, sid) VALUES (#{uid}, #{sid})")
    Integer addBalance(BalanceParam param);
    // FUNCTION 修改
    @Update("UPDATE balance " +
            "SET balance = balance + #{balance}, content = #{content} " +
            "WHERE uid = #{uid} AND sid =#{sid} ")
    Integer updateBalance(BalanceParam param);

}
