package com.tosh.whisper.manager;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class HttpClientManager
{
    private static HttpClientManager manager = new HttpClientManager();
    
    private SslContextFactory sslContextFactory = new SslContextFactory(true);
    
    private HttpClient client = new HttpClient(sslContextFactory);
    
    private HttpClientManager()
    {
        try
        {
            client.start();
        }
        catch (Exception e)
        {
            System.err.println("Http client start exception");
        }
    }
    
    public static HttpClientManager getInstance()
    {
        return manager;
    }
    
    public HttpClient getHttpClient()
    {
        return this.client;
    }
    
}
