package com.deng.service.impl;

import com.deng.mapper.DeptMapper;
import com.deng.mapper.EmpMapper;
import com.deng.pojo.Dept;
import com.deng.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    DeptMapper deptMapper;
    @Resource
    EmpMapper empMapper;

    @Override
    public List<Dept> select() {
        List<Dept> deptList = deptMapper.select();
        return deptList;
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
        empMapper.deleteByDeptId(id);
    }

    @Override
    public void insert(Dept dept) {
        deptMapper.insert(dept);
    }

    @Override
    public Dept selectById(Integer id) {
        Dept dept = deptMapper.selectById(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}
