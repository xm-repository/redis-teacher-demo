package com.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author cj
 * @date 2018/11/21
 */
@Component
public class RedisHello {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public  void hello(){
        //opsForValue就是操作string类型
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("test", "hello redis in spring");
        System.out.println("-----debug: redisTemplate = " + valueOperations.get("test"));
    }
}
