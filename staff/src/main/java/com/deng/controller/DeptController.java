package com.deng.controller;

import com.deng.anno.Log;
import com.deng.pojo.Dept;
import com.deng.pojo.Result;
import com.deng.service.DeptService;
import com.deng.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Resource
    DeptService deptService;

    @GetMapping
    Result select() {
        log.info("查询部门信息");
        List<Dept> deptList = deptService.select();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/{id}")
    Result deleteById(@PathVariable Integer id) {
        log.info("根据id删除部门信息:{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    Result insert(@RequestBody Dept dept) {
        log.info("新增部门信息:{}", dept.getName());
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    Result selectById(@PathVariable Integer id) {
        log.info("根据id查询部门信息:{}", id);
        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    Result update(@RequestBody Dept dept) {
        log.info("修改部门信息:{}", dept.getName());
        dept.setUpdateTime(LocalDateTime.now());
        deptService.update(dept);
        return Result.success();
    }
}
