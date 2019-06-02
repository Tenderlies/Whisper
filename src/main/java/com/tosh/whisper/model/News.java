package com.tosh.whisper.model;


public class News
{
    private String newsid;
    private String newstitle;
    private String keyword;
    private String content;
    
    public String getNewsid()
    {
        return newsid;
    }
    
    public void setNewsid(String newsid)
    {
        this.newsid = newsid;
    }
    
    public String getNewstitle()
    {
        return newstitle;
    }
    
    public void setNewstitle(String newstitle)
    {
        this.newstitle = newstitle;
    }
    
    public String getKeyword()
    {
        return keyword;
    }
    
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
}
