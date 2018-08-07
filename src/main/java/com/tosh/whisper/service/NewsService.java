package com.tosh.whisper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tosh.whisper.dao.mapper.NewsMapper;
import com.tosh.whisper.model.News;
import com.tosh.whisper.support.datasource.annotation.DataSource;

@Service
public class NewsService
{
    @Autowired
    public NewsMapper mapper;
    
    @DataSource("mysql")
    public News getNews(String id)
    {
        return mapper.getNews(id);
    }
    
    @DataSource("mysql")
    public List<News> getNewsList()
    {
        return mapper.getNewsList();
    }
}
