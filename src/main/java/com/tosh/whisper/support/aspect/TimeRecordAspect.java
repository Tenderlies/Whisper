package com.tosh.whisper.support.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeRecordAspect {

    private long time;

    @Pointcut(value = "execution(* com.tosh.whisper.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        time = new Date().getTime();
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        long elapsed = new Date().getTime() - time;
        System.out.println(elapsed + "ms -> " + method);
    }
}
