package com.tosh.whisper.support.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource
{
    public static Map<String, DataSource> customDataSources;
    
    public DynamicDataSource(Map<Object, Object> customDataSources,
            DataSource defaultTargetDataSource)
    {
        this.setDefaultTargetDataSource(defaultTargetDataSource);
        this.setTargetDataSources(customDataSources);
        this.afterPropertiesSet();
    }
    
    static class DataSourceHolder
    {
        private static ThreadLocal<String> dataSource = new ThreadLocal<String>();
        
        public static String getDataSource()
        {
            return dataSource.get();
        }
        
        public static void setDataSource(String dataSource)
        {
            DataSourceHolder.dataSource.set(dataSource);
        }
        
        public static void clearDataSource()
        {
            DataSourceHolder.dataSource.remove();
        }
    }
    
    public static void setDataSource(String dataSource)
    {
        DataSourceHolder.setDataSource(dataSource);
    }
    
    public static String getDataSource()
    {
        return DataSourceHolder.getDataSource();
    }
    
    public static void clearDataSource()
    {
        DataSourceHolder.dataSource.remove();
    }
    
    @Override
    protected Object determineCurrentLookupKey()
    {
        return getDataSource();
    }
    
}