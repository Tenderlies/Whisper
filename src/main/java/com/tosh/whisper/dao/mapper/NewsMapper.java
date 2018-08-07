package com.tosh.whisper.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tosh.whisper.model.News;
import com.tosh.whisper.support.datasource.annotation.DataSource;

@Mapper
public interface NewsMapper
{
    int insertSelective(News news);
    
    int delete(String newsid);
    
    @DataSource("mysql")
    News getNews(String newsid);
    
    List<News> getNewsList();
}
