package com.controller.chat.encoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

/**
 * @author cj
 * @date 2018/11/16
 */
public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
    @Override
    public ChatMessage decode(String s) throws DecodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessage message = null;
        try {
            message = objectMapper.readValue(s, ChatMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("消息编码失败");
        }
        return  message;
    }

    /**
     * 返回true才会真正进入到decode方法进行解码操作
     * @param s
     * @return
     */
    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
