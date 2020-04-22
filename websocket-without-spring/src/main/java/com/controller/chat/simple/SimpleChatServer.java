package com.controller.chat.simple;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;

/** 演示广播消息的发送
 * @author cj
 * @date 2018-12-04
 */

@ServerEndpoint("/chatsimple")
public class SimpleChatServer {

    @OnOpen
    public  void onOpen(Session session) throws IOException {
        session.getBasicRemote().sendText("from server on open---");
    }

    @OnMessage
    public  void onMsg(Session session,String msg){
        System.out.println("--server received msg: " + msg);
        Set<Session> sessionSet = session.getOpenSessions();
        for (Session connectedSession : sessionSet) {
            try {
                if(connectedSession.isOpen()) {
                    connectedSession.getBasicRemote().sendText(msg + " from server");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
