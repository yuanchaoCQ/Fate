package com.yc.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//以java的形式配置bean的注解
@Configuration
// 可以异步的
@EnableAsync
// 允许定时任务
@EnableScheduling
// mybatis的映射文件路径
//@MapperScan(basePackages = "com.wk.yc.mvc.dao.mapper")
// 事务管理
@EnableTransactionManagement
// spring的bean扫描器 排除controller的扫描
@ComponentScan(basePackages = "com.Yc", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class AppConfig {
    // 将所有的配置文件加到properties里
    public Properties getProperties() {
        Properties properties = new Properties();
        Resource[] resources = new Resource[] { new ClassPathResource("cons.properties"),
                new ClassPathResource("jdbc.properties"), };
        for (Resource resource : resources) {
            try {
                PropertiesLoaderUtils.fillProperties(properties, new EncodedResource(resource, "UTF-8"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return properties;
    }

    // c3p0的mysql数据源
    @Bean
    public ComboPooledDataSource getComboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Properties properties = getProperties();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setUser(properties.getProperty("mysql_username"));
        dataSource.setPassword(properties.getProperty("mysql_pwd"));
        dataSource.setJdbcUrl(properties.getProperty("mysql_jdbc_url"));

        dataSource.setMaxPoolSize(100);
        dataSource.setMinPoolSize(10);
        dataSource.setInitialPoolSize(60);
        dataSource.setMaxIdleTime(60);
        dataSource.setCheckoutTimeout(3000);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxStatements(8);
        dataSource.setMaxStatementsPerConnection(5);
        return dataSource;
    }
    //redis连接池
    @Bean
    public JedisPool getJedisPool() {
        Properties properties = getProperties();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(64);
        config.setMaxIdle(20);
        config.setMaxWaitMillis(10);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        JedisPool jedisPool = new JedisPool(config, properties.getProperty("redis_ip"),
                Integer.valueOf(properties.getProperty("redis_port")));
        return jedisPool;
    }
}
