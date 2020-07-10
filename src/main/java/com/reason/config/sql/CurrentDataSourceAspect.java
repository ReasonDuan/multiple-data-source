package com.reason.config.sql;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Reason.H.Duan
 * @Date: 7/10/2020
 */
@Aspect
@Component
public class CurrentDataSourceAspect {

    @Before("@annotation(currentDataSource)")
    public void beforeSwitchDB(JoinPoint point, CurrentDataSource currentDataSource){
        String dbName = currentDataSource.value();
        DynamicDataSource.setDB(dbName);
    }

    @After("@annotation(currentDataSource)")
    public void afterSWitchDB(JoinPoint point, CurrentDataSource currentDataSource){
        DynamicDataSource.clearDB();
    }

}
