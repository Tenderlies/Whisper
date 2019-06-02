package com.tosh.whisper.manager;

import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import com.tosh.whisper.AppConfig;

public class ServerConnectorManger {
    public static ServerConnector getHttpConnctor(Server server, int port) {
        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setPort(port);
        return httpConnector;
    }

    public static ServerConnector getHttpsConnctor(Server server, int port,
                                                   SslContextFactory sslContextFactory) {
        ServerConnector httpsConnector = new ServerConnector(server,
                sslContextFactory, new HttpConnectionFactory(
                AppConfig.getHttpsConfig()));
        httpsConnector.setPort(port);
        return httpsConnector;
    }

}
