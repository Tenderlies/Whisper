package com.tosh.whisper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tosh.whisper.model.News;
import com.tosh.whisper.service.NewsService;

@Component
@Path("/")
public class Whisper {
    @Autowired
    public NewsService service;

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(@Context HttpServletRequest request) throws Exception {
        return "abc";
    }

    @GET
    @Path("mybatis")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> mybatis() {
        return service.getNewsList();
    }

}
