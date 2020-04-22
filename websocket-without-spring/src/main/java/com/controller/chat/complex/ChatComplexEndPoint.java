package com.controller.chat.complex;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cj
 * @date 2018-12-06
 */

@ServerEndpoint("/chatcomplex/{username}")
public class ChatComplexEndPoint {


    private static ConcurrentHashMap<String,Session> connectedUsers = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("username") String username){

        connectedUsers.put(username, session);

        String allOnlineUsers = getAllOnlineUsers();
        notifyAllOnlinUsers(allOnlineUsers);

    }

    private void notifyAllOnlinUsers(String allUsers) {
        connectedUsers.forEach((name,s)->{
            try {
                s.getBasicRemote().sendText(allUsers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private String getAllOnlineUsers() {
        Set<String> users =  connectedUsers.keySet();
        StringBuilder sb = new StringBuilder();
        users.forEach(u-> {

            sb.append(u);
            sb.append(",");
        });
        String result = sb.toString().substring(0,sb.toString().length()-1);
        return  result;
    }
    /**
     * 下面的代码其实已经实现了广播
     * @param msg
     */
    @OnMessage
    public  void onMessage(String msg){
        String[] msgArray = msg.split(":");
        String targetUser = msgArray[0];
        String targetMsg = "msg:" + msgArray[1];

        if("*".equals(targetUser)){
            sendBroadcast(targetMsg);
        }else{
            sendPeer(targetUser,targetMsg);
        }

    }


    private  void sendBroadcast(String msg) {
        connectedUsers.forEach((name,s)->{
            try {
                s.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private  void sendPeer(String targetUser,String msg) {
        Session session = connectedUsers.get(targetUser);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
