package com.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cj
 * @date 2018/11/21
 */
public class Main {
    public static void main(String[] args) {
        //lettuceWithDefaultSerializer();
        lettuceWithJacksonSerializer();
    }

    private static void lettuceWithDefaultSerializer() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisPojoHello redisHello = applicationContext.getBean(RedisPojoHello.class);
        redisHello.hello();
    }

    private static void lettuceWithJacksonSerializer() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfigWithJacksonSerializer.class);
        RedisPojoHello redisHello = applicationContext.getBean(RedisPojoHello.class);
        redisHello.hello();
    }

}
