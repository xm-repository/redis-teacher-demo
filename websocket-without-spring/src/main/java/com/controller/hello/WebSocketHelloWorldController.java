package com.controller.hello;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 这个web socket 的hello world案例相当于一个echo案例
 * @author cj
 * @date 2018/11/15
 */

@ServerEndpoint("/hello")
public class WebSocketHelloWorldController  {


    /**
     * 只要有客户端连接进来就会创建一个新的对象
     */
    public WebSocketHelloWorldController() {
        System.out.println("---hello world server endpoint constructor---");
    }

    /**
     * 在OnOpen方法中抛出异常,会引发服务端的onError方法被调用
     * 但客户端不会引发错误,而是会引发客户端的onclose方法被调用
     *
     * 如果服务端没有OnError方法,那么整个程序并不崩溃,客户端的行为与
     * 服务端有OnError方法时的行为是一样的.
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("---session id: "  + session.getId());
       // throw new RuntimeException("cannot opened---");
        //System.out.println("---connected连接");
    }

    @OnMessage
    public  void onMsg(Session session,String msg) throws IOException {
        System.out.println("---message received :" + msg);
        System.out.println("---reply client");
        session.getBasicRemote().sendText("---from server");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        session.close();

    }

    /**
     * 在OnOpen代码里面直接抛出异常,在@OnError里面不调用session.close()方法
     * 客户端的连接状态(readyState)是CLOSED的
     *
     * @param session
     * @param throwable
     */
    @OnError
    public  void onError(Session session,Throwable throwable){
        System.out.println("-----debug: throwable = " + throwable);
        System.out.println("-----debug: session = " + session);
    }
}
