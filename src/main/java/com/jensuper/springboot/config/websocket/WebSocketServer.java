package com.jensuper.springboot.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: WebSocketServer
 * @Description:
 * @author:jichao
 * @date: 2019/5/31
 * @Copyright: 2019/5/31 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocketServer {

    private Session session;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketServersSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketServersSet.add(this);
        log.info("【webSocket消息】有新的连接消息，总数 = {}", webSocketServersSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketServersSet.remove(this);
        log.info("【webSocket消息】连接断开，总数 = {}", webSocketServersSet.size());
    }

    @OnMessage
    public void onMessage(String msg) {
        log.info("【webSocket消息】服务端收到消息，msg = {}", msg);
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(String msg) {
        webSocketServersSet.stream().forEach(socket -> {
            try {
                log.info("【webSocket消息】发送消息 消息体 = {}", msg);
                socket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
