package com;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;

/**
 * @author cj
 * @date 2020/4/13
 */
public class LettuceUtils {
    private static StatefulRedisConnection connection;

    static {
        RedisURI redisURI = RedisURI.Builder
                .redis("localhost")
                .withPort(6379)
               // .withPassword("123456")
                .withDatabase(0)
                .withTimeout(Duration.ofSeconds(5))
                .build();
        connection = RedisClient.create(redisURI).connect();
    }

    /**
     * Sync
     * @return
     */
    public static RedisCommands getCommands() {
        return connection.sync();
    }

    /**
     * Async
     * @return
     */
    public static RedisAsyncCommands getAsyncCommands(){
        return connection.async();
    }

    /**
     * Reactive
     * @return
     */
    public static RedisReactiveCommands getReactiveCommands(){
        return connection.reactive();
    }
}
