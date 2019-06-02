package com.tosh.whisper.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil
{
    private static ObjectMapper om = new ObjectMapper();
    
    public static <T> T parseObject(String jsonStr, Class<T> clazz)
    {
        T obj = null;
        try
        {
            obj = om.readValue(jsonStr, clazz);
        }
        catch (IOException e)
        {
            System.err.print("String parse to object exception!");
        }
        return obj;
    }
    
    public static List<Object> parseList(String jsonStr)
    {
        return parseList(jsonStr, Object.class);
    }
    
    public static <T> List<T> parseList(String jsonStr, Class<T> clazz)
    {
        List<T> objList = null;
        JavaType type = om.getTypeFactory().constructCollectionType(List.class,
                clazz);
        try
        {
            objList = om.readValue(jsonStr, type);
        }
        catch (IOException e)
        {
            System.err.print("String parse to list exception!");
        }
        return objList;
    }
    
    public static Map<String, Object> parseMap(String jsonStr)
    {
        return parseMap(jsonStr, String.class, Object.class);
    }
    
    public static <T> Map<String, T> parseMap(String jsonStr,
            Class<T> valueClazz)
    {
        return parseMap(jsonStr, String.class, valueClazz);
    }
    
    public static <K, V> Map<K, V> parseMap(String jsonStr, Class<K> keyClazz,
            Class<V> valueClazz)
    {
        Map<K, V> map = null;
        JavaType type = om.getTypeFactory().constructMapType(Map.class,
                keyClazz, valueClazz);
        try
        {
            map = om.readValue(jsonStr, type);
        }
        catch (IOException e)
        {
            System.err.print("String parse to map exception!");
        }
        return map;
    }
    
    public static String toJSONString(Object obj)
    {
        String jsonStr = null;
        try
        {
            jsonStr = om.writeValueAsString(obj);
        }
        catch (JsonProcessingException e)
        {
            System.err.print("Object to json string exception!");
        }
        return jsonStr;
    }
    
    private JsonUtil()
    {
        
    }
    
}
