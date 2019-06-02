package com.tosh.whisper.support;

import java.net.HttpCookie;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import com.tosh.whisper.manager.HttpClientManager;

public class RestClient
{
    public static HttpClient client = HttpClientManager.getInstance()
            .getHttpClient();
    
    public static ContentResponse get(String url) throws Exception
    {
        return get(url, null);
    }
    
    public static ContentResponse get(String url, Map<String, String> headers)
            throws Exception
    {
        return get(url, headers, null);
    }
    
    public static ContentResponse get(String url, Map<String, String> headers,
            HttpCookie cookie) throws Exception
    {
        return request(url, HttpMethod.GET, headers, null, cookie);
    }
    
    public static ContentResponse post(String url) throws Exception
    {
        return post(url, null, null);
    }
    
    public static ContentResponse post(String url, String rawData)
            throws Exception
    {
        return post(url, null, rawData);
    }
    
    public static ContentResponse post(String url, Map<String, String> headers)
            throws Exception
    {
        return post(url, headers, null);
    }
    
    public static ContentResponse post(String url, Map<String, String> headers,
            String rawData) throws Exception
    {
        return post(url, headers, rawData, null);
    }
    
    public static ContentResponse post(String url, Map<String, String> headers,
            String rawData, HttpCookie cookie) throws Exception
    {
        return request(url, HttpMethod.POST, headers, rawData, cookie);
    }
    
    public static ContentResponse put(String url) throws Exception
    {
        return put(url, null, null);
    }
    
    public static ContentResponse put(String url, String rawData)
            throws Exception
    {
        return put(url, null, rawData);
    }
    
    public static ContentResponse put(String url, Map<String, String> headers)
            throws Exception
    {
        return put(url, headers, null);
    }
    
    public static ContentResponse put(String url, Map<String, String> headers,
            String rawData) throws Exception
    {
        return put(url, headers, rawData, null);
    }
    
    public static ContentResponse put(String url, Map<String, String> headers,
            String rawData, HttpCookie cookie) throws Exception
    {
        return request(url, HttpMethod.PUT, headers, rawData, cookie);
    }
    
    public static ContentResponse delete(String url) throws Exception
    {
        return delete(url, null, null);
    }
    
    public static ContentResponse delete(String url, String rawData)
            throws Exception
    {
        return delete(url, null, rawData);
    }
    
    public static ContentResponse delete(String url, Map<String, String> headers)
            throws Exception
    {
        return delete(url, headers, null);
    }
    
    public static ContentResponse delete(String url,
            Map<String, String> headers, String rawData) throws Exception
    {
        return delete(url, headers, rawData, null);
    }
    
    public static ContentResponse delete(String url,
            Map<String, String> headers, String rawData, HttpCookie cookie)
            throws Exception
    {
        return request(url, HttpMethod.DELETE, headers, rawData, cookie);
    }
    
    public static ContentResponse request(String url, HttpMethod method,
            Map<String, String> headers, String rawData, HttpCookie cookie)
            throws Exception
    {
        Request request = client.newRequest(url);
        request.method(method);
        
        if (rawData != null && !rawData.isEmpty())
        {
            request.content(new StringContentProvider(rawData, "utf-8"));
        }
        
        if (headers != null)
        {
            for (Entry<String, String> header : headers.entrySet())
            {
                request.header(header.getKey(), header.getValue());
            }
        }
        if (cookie != null)
        {
            request.cookie(cookie);
        }
        
        return request.send();
    }
}
