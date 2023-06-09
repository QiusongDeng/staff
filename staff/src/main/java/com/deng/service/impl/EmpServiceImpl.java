package com.deng.service.impl;

import com.deng.mapper.EmpMapper;
import com.deng.pojo.Emp;
import com.deng.pojo.PageBean;
import com.deng.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;

    @Override
    public PageBean pageSelect(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.pageSelect(name, gender, begin, end);
        Page<Emp> empPage = (Page<Emp>) empList;
        PageBean pageBean = new PageBean(empPage.getTotal(), empPage.getResult());
        return pageBean;
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        empMapper.deleteByIds(ids);
    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp selectById(Short id) {
        Emp emp = empMapper.selectById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.selectByUsernameAndPassword(emp);
    }
}
