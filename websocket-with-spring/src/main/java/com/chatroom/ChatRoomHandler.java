package com.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cj
 * @date 2018-12-05
 */
public class ChatRoomHandler extends TextWebSocketHandler {

    @Autowired
    private DemoService demoService;
    private static final ConcurrentHashMap<String,WebSocketSession> connectedUsers = new ConcurrentHashMap<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        demoService.doInService();
        String username = session.getAttributes().get("username").toString();

        connectedUsers.put(username, session);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msgText = message.getPayload();

        if (msgText.contains(":")) {
            String[] text = msgText.split(":");
            String dest = text[0];
            String msg = text[1];
            WebSocketSession targetSession = connectedUsers.get(dest);
            if (targetSession != null) {
                TextMessage responseMessage = new TextMessage(msg + " from server" );
                targetSession.sendMessage(responseMessage);
            }
            return;
        }
        TextMessage responseMessage = new TextMessage(msgText + " from server");
        sendMessageBroadcast(responseMessage);
    }

    private void sendMessageBroadcast(TextMessage textMessage) {
        connectedUsers.forEach((username,session)->{
            try {
                session.sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
