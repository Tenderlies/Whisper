package com.tosh.whisper;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.tosh.whisper.support.datasource.DynamicDataSource;
import com.tosh.whisper.utils.PathUtil;

@Configuration
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.tosh.whisper" })
public class AppConfig
{
    @Bean(name = "mysqlDataSource")
    public static DataSource getMysqlDataSource() throws Exception
    {
        Map<String, String> config = new HashMap<String, String>();
        config.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME,
                com.mysql.jdbc.Driver.class.getName());
        config.put(DruidDataSourceFactory.PROP_NAME, "news");
        config.put(DruidDataSourceFactory.PROP_USERNAME, "root");
        config.put(DruidDataSourceFactory.PROP_PASSWORD, "10");
        config.put(DruidDataSourceFactory.PROP_URL,
                "jdbc:mysql://127.0.0.1:3306/demodb?serverTimezone=UTC&useSSL=true");
        
        return DruidDataSourceFactory.createDataSource(config);
    }
    
    @Bean(name = "h2DataSource")
    public static DataSource getH2DataSource() throws Exception
    {
        final String url = "jdbc:h2:~/whisper/db";
        final String user = "root";
        final String pwd = "10";
        Map<String, String> config = new HashMap<String, String>();
        config.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME,
                org.h2.Driver.class.getName());
        config.put(DruidDataSourceFactory.PROP_USERNAME, user);
        config.put(DruidDataSourceFactory.PROP_PASSWORD, pwd);
        config.put(DruidDataSourceFactory.PROP_URL, url);
        return DruidDataSourceFactory.createDataSource(config);
        
    }
    
    @Bean(name = "dynamicDataSource")
    public static DataSource getDynamicDataSource() throws Exception
    {
        Map<Object, Object> customDataSources = new HashMap<Object, Object>();
        customDataSources.put("mysql", getMysqlDataSource());
        customDataSources.put("h2", getH2DataSource());
        return new DynamicDataSource(customDataSources, getMysqlDataSource());
    }
    
    @Bean(name = "sqlSessionFactory")
    public static SqlSessionFactoryBean getSqlSesssionFactoryBean()
            throws Exception
    {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(getDynamicDataSource());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return bean;
    }
    
    @Bean
    public static MapperScannerConfigurer getMapperScannerConfigurer()
    {
        MapperScannerConfigurer conf = new MapperScannerConfigurer();
        conf.setBasePackage("com.tosh.whisper.dao.mapper");
        conf.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return conf;
    }
    
    @Bean(name = "customDataSources")
    public static Map<Object, Object> getCustomDataSources() throws Exception
    {
        Map<Object, Object> customDataSources = new HashMap<Object, Object>();
        customDataSources.put("mysql", getMysqlDataSource());
        customDataSources.put("h2", getH2DataSource());
        return customDataSources;
    }
    
    @Bean(name = "serverSslContextFactory")
    public static SslContextFactory getServerSslContextFactory()
    {
        SslContextFactory sslContextFactory = new SslContextFactory(true);
        sslContextFactory.setKeyStoreType("JKS");
        sslContextFactory.setKeyStorePath(PathUtil.getRootPath()
                + "cert/whisper.keystore");
        sslContextFactory.setKeyStorePassword("123456");
        sslContextFactory.setKeyManagerPassword("123456");
        return sslContextFactory;
    }
    
    @Bean(name = "httpsConfig")
    public static HttpConfiguration getHttpsConfig()
    {
        HttpConfiguration httpsConfig = new HttpConfiguration();
        httpsConfig.setSecureScheme("https");
        httpsConfig.setOutputBufferSize(32768);
        httpsConfig.addCustomizer(new SecureRequestCustomizer());
        return httpsConfig;
    }
    
}
