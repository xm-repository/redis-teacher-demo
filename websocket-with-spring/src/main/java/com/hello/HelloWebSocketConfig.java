package com.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *
 *
 * 下面的配置类等价于下面这个xml配置形式
 *      <websocket:handlers>
 *         <websocket:mapping path="/myHandler" handler="myHandler"/>
 *     </websocket:handlers>
 *
 *     <bean id="myHandler" class="org.springframework.samples.MyHandler"/>
 *
 *
 * @author cj
 * @date 2018/11/19
 */

@Configuration
@EnableWebSocket
public class HelloWebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //下面的拦截器在这个案例中可以不用添加,下面添加的拦截器是把
        // HttpSession属性传递给WebSocket session.
        registry
            .addHandler(helloHandler(), "/hello");

    }

    @Bean
    public WebSocketHandler helloHandler(){
        return  new HelloHandler();
    }
}
