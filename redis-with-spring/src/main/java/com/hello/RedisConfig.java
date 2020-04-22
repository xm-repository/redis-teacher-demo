package com.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author cj
 * @date 2018/11/21
 */
@Configuration
@ComponentScan("com.hello")
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        //RedisStandaloneConfiguration:redis单体(非集群)下的配置
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    /**
     * Spring Data项目的其中一个特点就是有一系列的Template类型操作存储层
     * @return
     */
    @Bean
        public StringRedisTemplate redisTemplate() {
            StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory());
            return redisTemplate;
    }
}