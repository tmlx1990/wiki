package com.siants.wiki.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint("/ws/{token}")
@Slf4j
public class WebSocketServer {

    /**
     * 每个客户端一个token
     */
    private String token = "";

    private static final Map<String, Session> map = new HashMap<>();

    /**
     * 连接成功
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        map.put(token, session);
        this.token = token;
        log.info("有新连接 : token : {}, session id : {} , 当前连接数 : {}", token, session.getId(), map.size());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(Session session) {
        map.remove(this.token);
        log.info("连接关闭, token : {}, session id : {}! 当前连接数 : {}", this.token, session.getId(),  map.size());
    }

    /**
     * 收到消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到消息 : {}, 内容 : {}", token, message);
    }

    /**
     * 连接错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误", error);
    }

    /**
     * 群发消息
     */
    public void sendInfo(String message) {
        for (String token : map.keySet()) {
            Session session = map.get(token);
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                log.error("推送消息失败 : {}, 内容 : {}", token, message);
            }
            log.info("推送消息 : {}, 内容 : {}", token, message);
        }
    }
}
