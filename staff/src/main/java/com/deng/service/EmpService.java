package com.deng.service;

import com.deng.pojo.Emp;
import com.deng.pojo.PageBean;

import java.time.LocalDate;

/**
 * 员工管理
 */
public interface EmpService {
    PageBean pageSelect(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    void deleteByIds(Integer[] ids);

    void insert(Emp emp);

    Emp selectById(Short id);

    void update(Emp emp);

    Emp login(Emp emp);
}
