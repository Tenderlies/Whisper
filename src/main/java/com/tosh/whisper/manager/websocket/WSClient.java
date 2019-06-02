package com.tosh.whisper.manager.websocket;

import java.net.URI;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class WSClient {
    public static void main(String[] args) throws Exception {
        WebSocketClient wsc = new WebSocketClient();
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        request.setHeader("test", "abc");
        WSHandler handler = new WSHandler();
        wsc.start();
        wsc.connect(handler, new URI("ws://127.0.0.1:9090/cool/"), request);
        Thread.sleep(100000);
    }

    @WebSocket
    public static class WSHandler {
        @OnWebSocketClose
        public void onWebSocketClose(int arg0, String arg1) {
            System.out.println("Server Close!");
        }

        @OnWebSocketConnect
        public void onWebSocketConnect(Session session) {
            System.out.println("Connect!");
        }

        @OnWebSocketMessage
        public void onWebSocketText(String arg0) {
            System.out.println("Server send :" + arg0);
        }

    }
}
