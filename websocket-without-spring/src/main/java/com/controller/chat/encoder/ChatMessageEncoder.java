package com.controller.chat.encoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author cj
 * @date 2018/11/16
 */
public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
    @Override
    public String encode(ChatMessage object) throws EncodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        try {
             result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("消息解码失败");
        }
        return result;

    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
