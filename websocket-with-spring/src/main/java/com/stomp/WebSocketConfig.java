package com.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 下面网址有关于spring中使用stomp的文章
 * https://juejin.im/post/5b7071ade51d45665816f8c0
 * @author cj
 * @date 2018/11/17
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //enableSimpleBroker开启的是一个内存中的消息代理(broker),把消息发送给以/topic
        //开头的客户端
        config.enableSimpleBroker("/topic");
        //此设置与@MessageMapping注解一起,设定了客户端发送消息的地址是/app/chat
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //这里注册2个同样的端点,一个支持sockjs
        // 一个不支持,以便浏览器不支持websocket时仍然能正常运行
        registry.addEndpoint("/chat");
        registry.addEndpoint("/chat").withSockJS();
    }
}
