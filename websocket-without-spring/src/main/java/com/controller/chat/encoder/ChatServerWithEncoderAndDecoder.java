package com.controller.chat.encoder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cj
 * @date 2018/11/16
 */
@ServerEndpoint(value = "/chat/{username}",
        encoders = {ChatMessageEncoder.class},
        decoders = {ChatMessageDecoder.class})
public class ChatServerWithEncoderAndDecoder {

    private  static  ConcurrentHashMap<Session,String> users = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session,@PathParam("username") String username) {
        if ( users.containsValue(username)){
            throw new RuntimeException("user exist");
        }else{
            users.put(session, username);
            notifyAllOnlineUsers();
        }
    }

    private void notifyAllOnlineUsers() {
        ChatMessage chatMessage = getOnlineUsersChatMessage();
        Enumeration<Session>  onlineSessions = users.keys();
        while (onlineSessions.hasMoreElements()) {
            Session onlineSession = onlineSessions.nextElement();
            try {
                onlineSession.getBasicRemote().sendObject(chatMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }

    }

    private ChatMessage getOnlineUsersChatMessage() {
        StringBuilder usernames = new StringBuilder();
        users.values().forEach(username->{
            usernames.append(username);
            usernames.append(",");
        });
        String onlineUsers = usernames.substring(0, usernames.length() - 1);
        System.out.println("-----debug: onlineUsers = " + onlineUsers);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setType("online");
        chatMessage.setOnlineUsers(onlineUsers);
        return chatMessage;
    }

    @OnMessage
    public void onMessage(Session session, ChatMessage chatMessage) {
        chatMessage.setType("msg");
        if ("*".equals(chatMessage.getDestination())) {
            sendMessageBroadcast(session,chatMessage);
        }else{
            sendMessagePeer(chatMessage);
        }
        notifyAllOnlineUsers();
    }

    private void sendMessagePeer(ChatMessage chatMessage) {



        users.forEach((session,username)->{
            if (username.equals(chatMessage.getDestination())) {
                try {
                    session.getBasicRemote().sendObject(chatMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendMessageBroadcast(Session session,ChatMessage chatMessage) {
        Enumeration<Session> enumeration = users.keys();
        while (enumeration.hasMoreElements()){
            Session userSession = enumeration.nextElement();
            if (userSession != session) {
                try {
                    userSession.getBasicRemote().sendObject(chatMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("send msg failed");
                } catch (EncodeException e) {
                    e.printStackTrace();
                    throw new RuntimeException("encoded failed");
                }
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable throwable) {

        System.out.println("-----debug: throwable = " + throwable);
    }
}
