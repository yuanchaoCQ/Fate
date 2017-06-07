package com.wk.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableAsync
@EnableScheduling // 定时任务
@EnableTransactionManagement // 事物管理
@MapperScan(basePackages = "com.wk.cpd.mvc.dao.mapper")
//@PropertySource({"classpath:jdbc.properties","classpath:const.properties"})
@ComponentScan(basePackages = "com.wk.cpd", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class AppConfig {

    /**
     * @title: getConfigProperties
     * @description: 读取properties文件
     * @return
     */
    private Properties getConfigProperties() {
        Properties properties = new Properties();
        Resource[] resources = { new ClassPathResource("jdbc.properties"), new ClassPathResource("const.properties") };
        for (Resource resource : resources) {
            try {
                PropertiesLoaderUtils.fillProperties(properties, new EncodedResource(resource, "UTF-8"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return properties;
    }

    /**
     * @title: getDatasource
     * @description: 配置MySQL数据源
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public ComboPooledDataSource datasource() throws PropertyVetoException {
        Properties properties = getConfigProperties();
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
//        dataSource.setUser("jdbc.MYSQL_USERNAME");
//        dataSource.setPassword("jdbc.MYSQL_PWD");
//        dataSource.setJdbcUrl("jdbc.JDBCURL");
        dataSource.setUser(properties.getProperty("MYSQL_USERNAME"));
        dataSource.setPassword(properties.getProperty("MYSQL_PWD"));
        dataSource.setJdbcUrl(properties.getProperty("JDBCURL"));

        dataSource.setMaxPoolSize(200);
        dataSource.setMinPoolSize(50);
        dataSource.setInitialPoolSize(60);
        dataSource.setMaxIdleTime(60);
        dataSource.setCheckoutTimeout(3000);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxStatements(8);
        dataSource.setMaxStatementsPerConnection(5);

        dataSource.setAutomaticTestTable("CPD_TEST_TABLE");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(datasource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(datasource());
        sqlSessionFactory.setTypeAliasesPackage("com.wk.cpd.mvc.dao.model");
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/wk/cpd/mvc/dao/mapping/*Mapper.xml")); // 如果mapping文件与java文件不在同一目录下
        return sqlSessionFactory.getObject();
    }
    // /**
    // * @title: getJedisPoolConfig
    // * @description: 配置redis连接池
    // * @return
    // */
    // private JedisPoolConfig getJedisPoolConfig() {
    // JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    // jedisPoolConfig.setMaxTotal(500);
    // jedisPoolConfig.setMaxIdle(20);
    // jedisPoolConfig.setMaxWaitMillis(10);
    // jedisPoolConfig.setTestOnBorrow(true);
    //
    // return jedisPoolConfig;
    // }

    // /**
    // * @title: jedisConnectionFactoryManager
    // * @description: redis连接工厂
    // * @return
    // * @throws Exception
    // */
    // @Bean(destroyMethod = "poolDestory")
    // public JedisConnectionFactoryManager jedisConnectionFactoryManager()
    // throws Exception {
    // Properties properties = new Properties();
    // Resource resources = new ClassPathResource("jdbc.properties");
    // PropertiesLoaderUtils.fillProperties(properties, new
    // EncodedResource(resources, "UTF-8"));
    // JedisConnectionFactoryManager jedisConnectionFactoryManager =
    // JedisConnectionFactoryManager.getInstance();
    // jedisConnectionFactoryManager.setAdxaddressKeyPrefix(properties.getProperty("feedAddressKeyPrefix"));
    // jedisConnectionFactoryManager.setPoolConfig(getJedisPoolConfig());
    // jedisConnectionFactoryManager.setMaxRedirections(Integer.valueOf(properties.getProperty("maxRetry")));
    // if (!(StringUtils.isEmpty(properties.getProperty("connectTimeout"))
    // || StringUtils.isEmpty(properties.getProperty("soTimeout")))) {
    //
    // jedisConnectionFactoryManager.setConnectTimeout(Integer.valueOf(properties.getProperty("connectTimeout")));
    // jedisConnectionFactoryManager.setSoTimeout(Integer.valueOf(properties.getProperty("soTimeout")));
    // }
    // jedisConnectionFactoryManager.setJedisConnectionFactories(properties);
    // return jedisConnectionFactoryManager;
    // }

    // @Bean
    // public MapperScannerConfigurer mapperScannerConfigurer() {
    // MapperScannerConfigurer mapperScannerConfigurer = new
    // MapperScannerConfigurer();
    // mapperScannerConfigurer.setBasePackage("");
    // mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
    // return mapperScannerConfigurer;
    // }

}
