package com.tosh.whisper.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.stereotype.Component;

@Component
@Path("/freemarker")
public class FreemarkerDemo
{
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getHello()
    {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", "Pavel");
        final List<String> list = new ArrayList<String>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        map.put("items", list);
        
        return new Viewable("/hello", map);
    }
    
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_HTML)
    public Viewable test()
    {
        return new Viewable("/test");
    }
}
