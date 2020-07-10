package com.reason.config.sql;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Description: 切面， 更改数据源
 * @Author: Reason.H.Duan
 * @Date: 7/10/2020
 */
@Aspect
@Component
public class CurrentDataSourceAspect {

    /**
     * 在CurrentDataSource执行前切换数据源
     * @param point 切面对象
     * @param currentDataSource 数据源切换注解对象
     */
    @Before("@annotation(currentDataSource)")
    public void beforeSwitchDB(JoinPoint point, CurrentDataSource currentDataSource){
        String dbName = currentDataSource.value();
        DynamicDataSource.setDB(dbName);
    }

    /**
     * 在CurrentDataSource执行后清湖当前数据源
     * @param point 切面对象
     * @param currentDataSource 数据源切换注解对象
     */
    @After("@annotation(currentDataSource)")
    public void afterSWitchDB(JoinPoint point, CurrentDataSource currentDataSource){
        DynamicDataSource.clearDB();
    }

}
