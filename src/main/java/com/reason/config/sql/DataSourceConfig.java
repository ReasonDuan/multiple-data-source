package com.reason.config.sql;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Reason.H.Duan
 * @Date: 7/10/2020
 */
@Configuration
@MapperScan(basePackages = "com.reason.mapper", sqlSessionFactoryRef = "dynamicSqlSessionFactory")
public class DataSourceConfig {

    @Bean(name = "dynamicSource")
    public DynamicDataSource dataSource(@Qualifier("db1") DataSource db1,
                                        @Qualifier("db2")DataSource db2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(db1);
        // 添加多数据源
        Map<Object, Object> dbMap = new HashMap<>();
        dbMap.put("db1", db1);
        dbMap.put("db2", db2);
        dynamicDataSource.setTargetDataSources(dbMap);
        return dynamicDataSource;
    }

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory dynamicSqlSessionFactory(@Qualifier(value = "dynamicSource") DynamicDataSource myRoutingDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(myRoutingDataSource);
        return factoryBean.getObject();
    }
}
