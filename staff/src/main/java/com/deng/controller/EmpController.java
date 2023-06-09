package com.deng.controller;

import com.deng.anno.Log;
import com.deng.pojo.Emp;
import com.deng.pojo.PageBean;
import com.deng.pojo.Result;
import com.deng.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * 员工管理Controller
 */
@Lazy
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Resource
    EmpService empService;

    @GetMapping
    public Result pageSelect(String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询,参数:{},{},{},{},{},{}", name, gender, begin, end, page, pageSize);
        PageBean pageBean = empService.pageSelect(name, gender, begin, end, page, pageSize);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable Integer[] ids) {
        log.info("批量删除员工：{}", Arrays.toString(ids));
        empService.deleteByIds(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp) {
        log.info("添加员工：{}", emp);
        empService.insert(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Short id) {
        log.info("按id查询员工：{}", id);
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
