package com.example.server.dao;

import com.example.server.dto.param.CompanionParam;
import com.example.server.entity.Companion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CompanionDao {
    // SECTION 陪娃管理
    // FUNCTION 获取
    List<Companion> getCompanionList(String query);
    // FUNCTION 添加
    @Insert("INSERT INTO companion (uid,sid) VALUES (#{uid}, #{sid})")
    Integer addCompanion(CompanionParam param);
    // FUNCTION 修改
    @Update("UPDATE companion SET status = #{status} WHERE id = #{id} ")
    Integer updateCompanion(Integer id, String status);
    // FUNCTION 删除
    @Update("UPDATE companion SET status = 'DELETED' WHERE id = #{id}")
    Integer deleteCompanion(Integer id);

}
