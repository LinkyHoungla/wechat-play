package com.example.server.dao;

import com.example.server.dto.param.GameParam;
import com.example.server.dto.param.ServiceParam;
import com.example.server.dto.param.USidParam;
import com.example.server.entity.Companion;
import com.example.server.entity.Game;
import com.example.server.entity.Service;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CompanionDao {
    // SECTION 陪玩管理
    // FUNCTION 获取
    List<Companion> getCompanionList(String query, String tag);
    // FUNCTION 添加
    @Insert("INSERT INTO companion (uid,sid) VALUES (#{uid}, #{sid})")
    Integer addCompanion(USidParam param);
    // FUNCTION 修改
    @Update("UPDATE companion SET status = #{status} WHERE id = #{id} ")
    Integer updateCompanion(Integer id, String status);
    // FUNCTION 删除
    @Update("UPDATE companion SET status = 'DELETED' WHERE id = #{id}")
    Integer deleteCompanion(Integer id);

    // SECTION 服务管理
    // FUNCTION 获取
    List<Service> getServiceList(String query, String tag);
    // FUNCTION 添加
    @Insert("INSERT INTO service (type, name, gender) VALUES (#{type}, #{name}, #{gender})")
    Integer addService(ServiceParam param);
    // FUNCTION 修改
    @Update("UPDATE service " +
            "SET name = #{name}, gender = #{gender} " +
            "WHERE id = #{id} ")
    Integer updateService(ServiceParam param);
    // FUNCTION 删除
    @Delete("DELETE FROM service WHERE id = #{id}")
    Integer deleteService(Integer id);

    // SECTION 游戏管理
    // FUNCTION 获取
    List<Game> getGameList(String query, String tag);
    // FUNCTION 添加
    @Insert("INSERT INTO game (pid, type, name) VALUES (#{pid}, #{type}, #{name})")
    Integer addGame(GameParam param);
    // FUNCTION 修改
    @Update("UPDATE game " +
            "SET name = #{name} " +
            "WHERE id = #{id} ")
    Integer updateGame(GameParam param);
    // FUNCTION 删除
    @Delete("DELETE FROM game WHERE id = #{id}")
    Integer deleteGame(Integer id);

}
