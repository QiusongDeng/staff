package com.deng.aop;

import com.alibaba.fastjson.JSONObject;
import com.deng.mapper.OperateLogMapper;
import com.deng.pojo.OperateLog;
import com.deng.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Resource
    private HttpServletRequest request;
    @Resource
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.deng.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        OperateLog operateLog = new OperateLog();
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        operateLog.setOperateUser((Integer) claims.get("id"));
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        Object object = joinPoint.proceed();
        operateLog.setReturnValue(JSONObject.toJSONString(object));
        long end = System.currentTimeMillis();
        operateLog.setCostTime(end - begin);
        operateLogMapper.insert(operateLog);
        return object;
    }
}
