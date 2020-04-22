package com;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;


/**
 * @author cj
 * @date 2018/11/21
 */
public class RedisLettuceHelloWorld {
    public static void main(String[] args) {
        lettuceHelloworld();
        //lettuceUtilsDemo();
    }

    private static void lettuceHelloworld() {
        //此类实例最好是单例的,这是个线程安全的类
        RedisClient client = RedisClient.create(RedisURI.create("redis://localhost:6379"));
        StatefulRedisConnection<String,String> connect = client.connect();

        /* 同步执行的命令 */
        RedisCommands<String,String> commands = connect.sync();
        commands.set("test","hello lettuce redis");
        //设置过期时间为600秒
        commands.expire("test", 600);
        String value=commands.get("test");
        System.out.println(value);
    }


    private static void lettuceUtilsDemo() {
        RedisCommands<String, String> commands = LettuceUtils.getCommands();
        commands.set("key", "Hello, Redis!");
        String value = commands.get("key");
        System.out.println(value);
    }
}
