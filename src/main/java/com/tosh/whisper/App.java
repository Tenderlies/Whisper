package com.tosh.whisper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.tosh.whisper.manager.ServerConnectorManger;


public class App {

    public static void main(String[] args) throws Exception {
        removeUSExportRestrictions();
        Server server = new Server();
        List<ServerConnector> connectors = new ArrayList<ServerConnector>();
        List<Handler> handlers = new ArrayList<Handler>();

        connectors.add(ServerConnectorManger.getHttpConnctor(server, 50080));
        connectors.add(ServerConnectorManger.getHttpsConnctor(server, 50413,
                AppConfig.getServerSslContextFactory()));

        FilterHolder customHodler = new FilterHolder(new CustomFilter());
        FilterHolder jerseyHodler = new FilterHolder(new ServletContainer(
                new JerseyConfig()));

        final ServletContextHandler handler = new ServletContextHandler(
                ServletContextHandler.SESSIONS);

        handler.addFilter(customHodler, "/*",
                EnumSet.allOf(DispatcherType.class));
        handler.addFilter(jerseyHodler, "/*",
                EnumSet.allOf(DispatcherType.class));

        AnnotationConfigWebApplicationContext springAnnotationConfigContext = new AnnotationConfigWebApplicationContext();
        springAnnotationConfigContext.register(AppConfig.class);
        handler.addEventListener(new ContextLoaderListener(
                springAnnotationConfigContext));

        handlers.add(handler);
        server.setConnectors(wrapConnectors(connectors));
        server.setHandler(wrapHandlers(handlers));
        server.join();
        server.start();
    }

    private static Connector[] wrapConnectors(List<ServerConnector> connectors) {
        Connector[] connectorArr = new Connector[connectors.size()];
        connectors.toArray(connectorArr);
        return connectorArr;
    }

    private static ContextHandlerCollection wrapHandlers(List<Handler> handlers) {
        ContextHandlerCollection handlerCollection = new ContextHandlerCollection();
        Handler[] handlerArr = new Handler[handlers.size()];
        handlers.toArray(handlerArr);
        handlerCollection.setHandlers(handlerArr);
        return handlerCollection;
    }

    private static void removeUSExportRestrictions() {
        try {
            Field field = Class.forName("javax.crypto.JceSecurity")
                    .getDeclaredField("isRestricted");
            field.setAccessible(true);

            field.set(null, Boolean.FALSE);
        } catch (Exception e) {
            System.err.println("Remove US export restrictions failed!");
        }
    }
}
