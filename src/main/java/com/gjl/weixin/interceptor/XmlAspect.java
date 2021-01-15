package com.gjl.weixin.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;


@Aspect
@Component
public class XmlAspect {
    private static final Logger LOG = LoggerFactory.getLogger(XmlAspect.class);
    @Around("execution(* com.gjl.weixin.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        LOG.info("切片开始。。。");
        long startTime = System.currentTimeMillis();
        // 获取请求入参
        Object[] args = proceedingJoinPoint.getArgs();
        Arrays.stream(args).forEach(arg -> LOG.info("arg is {}", arg));
        // 获取相应
        Object response = proceedingJoinPoint.proceed();
       // Map<String,Object> mapb = (Map<String,Object>)response;

       // System.out.println("XmlAspect"+mapb.get("checkup_date")+"              "+mapb.get("age"));

        long endTime = System.currentTimeMillis();
        LOG.info("请求:{}, 耗时{}ms", proceedingJoinPoint.getSignature(), (endTime - startTime));
        LOG.info("切片结束。。。");
        return null;
    }
}
