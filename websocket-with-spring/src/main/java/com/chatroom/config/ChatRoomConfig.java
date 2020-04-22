package com.chatroom.config;

import com.chatroom.ChatRoomHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author cj
 * @date 2018-12-05
 */
@Configuration
@EnableWebSocket
public class ChatRoomConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(textWebSocketHandler(), "/chatroom")
            .addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    @Bean
    public TextWebSocketHandler textWebSocketHandler(){
        return new ChatRoomHandler();
    }
}
