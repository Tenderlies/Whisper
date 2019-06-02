package com.tosh.whisper.manager.websocket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WSServer extends WebSocketHandler implements WebSocketListener {

    public static void main(String[] args) throws Exception {
        Server server = new Server(9090);
        ContextHandler handler = new ContextHandler();
        handler.setContextPath("/cool");
        handler.setHandler(new WSServer());

        ContextHandlerCollection chc = new ContextHandlerCollection(handler);
        server.setHandler(chc);
        server.join();
        server.start();
    }

    @Override
    public void configure(WebSocketServletFactory arg0) {
        arg0.register(WSServer.class);
    }

    @Override
    public void onWebSocketConnect(Session session) {
        System.out.println("Connect!");
    }

    @Override
    public void onWebSocketError(Throwable arg0) {
        System.out.println("Error!");
    }

    @Override
    public void onWebSocketBinary(byte[] arg0, int arg1, int arg2) {

    }

    @Override
    public void onWebSocketText(String arg0) {
        System.out.println("Client: arg0");
    }

    @Override
    public void onWebSocketClose(int arg0, String arg1) {

    }

}
