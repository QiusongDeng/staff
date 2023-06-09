package com.deng.service;

import com.deng.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    List<Dept> select();

    void deleteById(Integer id);

    void insert(Dept dept);

    Dept selectById(Integer id);

    void update(Dept dept);
}
