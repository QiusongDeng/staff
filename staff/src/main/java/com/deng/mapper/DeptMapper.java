package com.deng.mapper;

import com.deng.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    @Select("select id,name,create_time,update_time from dept")
    List<Dept> select();

    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept selectById(Integer id);

    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(id, name, create_time, update_time) VALUES (#{id},#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
