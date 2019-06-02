package com.tosh.whisper.support.datasource.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.tosh.whisper.support.datasource.DynamicDataSource;
import com.tosh.whisper.support.datasource.annotation.DataSource;

@Aspect
@Component
public class DataSourceAspect
{
    @Pointcut("@annotation(ds))")
    public void pointCut(DataSource ds)
    {
        
    }
    
    @Before("pointCut(ds)")
    public void before(JoinPoint joinPoint, DataSource ds) throws Exception
    {
        System.out.println(ds.value() + "...");
        DynamicDataSource.setDataSource(ds.value());
    }
    
    @After("pointCut(ds))")
    public void after(JoinPoint joinPoint, DataSource ds)
    {
        System.out.println(ds.value() + "!!!");
        DynamicDataSource.clearDataSource();
    }
    
}
