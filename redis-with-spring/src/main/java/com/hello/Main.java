package com.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cj
 * @date 2018/11/21
 */
public class Main {
    public static void main(String[] args) {
        lettuceWithSpringHelloworld();
    }

    private static void lettuceWithSpringHelloworld() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisHello redisHello = applicationContext.getBean(RedisHello.class);
        redisHello.hello();
    }



}
