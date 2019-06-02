/**
 * Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 *
 * @author taoshuang
 * @version 1.0
 * @since 2017年1月3日
 */
package com.tosh.whisper.manager.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tosh.whisper.utils.StringUtil;

/**
 * ClassName: WebSocketManager<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 *
 * @author taoshuang
 * @since 2017年1月3日
 */
@ServerEndpoint("/websocket")
public class WebSocketServer {
    private static Logger logger = LoggerFactory
            .getLogger(WebSocketServer.class);

    private static Set<Session> sessionSet = new CopyOnWriteArraySet<Session>();

    private static AtomicInteger sessionCount = new AtomicInteger(0);

    @OnOpen
    public void onOpen(Session session) {
        cacheSession(session);
        sessionCount.incrementAndGet();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        for (Session sessionItem : sessionSet) {
            sendMessage(sessionItem, message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        removeSession(session);
        sessionCount.decrementAndGet();
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("WebSocket Connect Error!", error.getMessage());
    }

    public void cacheSession(Session session) {
        sessionSet.add(session);
    }

    public void removeSession(Session session) {
        sessionSet.remove(session);
    }

    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(StringUtil.htmlFilter(message));
        } catch (IOException e) {
            logger.error("Server send WebSocket message failed!");
        }
    }
}
