/**
 * Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 *
 * @author taoshuang
 * @version 1.0
 * @since 2017年1月4日
 */
package com.tosh.whisper.manager.websocket;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tosh.whisper.utils.StringUtil;

/**
 * ClassName: WebSocketClient<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 *
 * @author taoshuang
 * @since 2017年1月4日
 */

@ClientEndpoint(subprotocols = "chat")
public class WebSocketClient {
    private static Logger logger = LoggerFactory
            .getLogger(WebSocketClient.class);

    private static WebSocketClient client = new WebSocketClient();

    private static URI uri;

    private static Session session;

    //注册线程，由Spring调用
    public void init() {
        try {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                        WebSocketClient.connectToServer(new URI(
                                "ws://127.0.0.1:8080/mavenweb/websocket"));
                    } catch (Exception e) {

                    }
                }
            }).start();

        } catch (Exception e) {
        }
    }

    public static WebSocketClient connectToServer(URI uri) throws Exception {
        WebSocketClient.uri = uri;
        WebSocketContainer wsc = ContainerProvider.getWebSocketContainer();
        session = wsc.connectToServer(WebSocketClient.class, uri);
        return client;
    }

    @OnOpen
    public void onOpen() {

    }

    @OnMessage
    public void onMessage(String message) {
        if ("client".equals(message)) {
            sendMessage("收到");
        }
    }

    @OnClose
    public void onClose(Session session) {
        try {
            session.close();
        } catch (IOException e) {
        }
    }

    @OnError
    public void onError(Session session, Throwable error) throws Throwable {
        logger.error("WebSocket Connect Error!", error.getMessage());
        try {
            session.close();
        } catch (IOException e) {
            logger.error("Client WebSocket Error!");
        }
        throw error;
    }

    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(StringUtil.htmlFilter(message));
        } catch (IOException e) {
            logger.error("Client send WebSocket message failed!");
        }
    }

    public WebSocketClient() {
    }

    public URI getUri() {
        return uri;
    }

    public Session getSession() {
        return session;
    }

}
