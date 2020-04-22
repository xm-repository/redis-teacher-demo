package com.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author cj
 * @date 2018/11/21
 */
@Component
public class RedisPojoHello {

    @Autowired
    private RedisTemplate redisTemplate;


    public void hello() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(10);
        userInfo.setName("abc");
        valueOperations.set("test", userInfo);

        UserInfo result = (UserInfo) valueOperations.get("test");
        System.out.println("-----debug: result = " + result);
    }
}
