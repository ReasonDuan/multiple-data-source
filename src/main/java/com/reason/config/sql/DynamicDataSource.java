package com.reason.config.sql;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description:
 * @Author: Reason.H.Duan
 * @Date: 7/10/2020
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置当前数据源
    public static void setDB(String dbName){
        contextHolder.set(dbName);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
