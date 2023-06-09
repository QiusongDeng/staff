package com.deng.controller;

import com.deng.pojo.Emp;
import com.deng.pojo.Result;
import com.deng.service.EmpService;
import com.deng.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Resource
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录：{}", emp);
        Emp e = empService.login(emp);
        if (e == null) return Result.error("用户名或命名错误");
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", e.getId());
        claims.put("name", e.getName());
        claims.put("username", e.getUsername());
        String jwt = JwtUtils.generateJwt(claims);
        return Result.success(jwt);
    }

}
