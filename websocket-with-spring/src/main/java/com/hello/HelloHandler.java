package com.hello;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author cj
 * @date 2018/11/19
 */
public class HelloHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       TextMessage textMessage = new TextMessage(message.getPayload() + " from server");
        session.sendMessage(textMessage);
    }
}
