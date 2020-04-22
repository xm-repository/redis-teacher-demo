package com.controller.chat.encoder;

import lombok.Data;

/**
 * @author cj
 * @date 2018/11/16
 */

@Data
public class ChatMessage {

    private String from;
    /**
     * 值为*的时候表示给其它所有其它用户发送消息
     */
    private  String destination;
    private String msg;
    /**
     * 所有的在线用户,以逗号分隔
     */
    private String onlineUsers;

    /**
     * 消息类型,现在只有两个值,一个是online表示发送所有在线用户,
     * 一个是msg,表示传递消息用.
     */
    private String type;

}
