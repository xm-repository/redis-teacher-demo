package com.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author cj
 * @date 2018/11/21
 */
@Configuration
@ComponentScan("com.pojo")
public class RedisConfigWithJacksonSerializer {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory());

        //使用StringRedisSerializer作为key的序列化器
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        //设置键的序列化器
        redisTemplate.setKeySerializer(keySerializer);
        //设置hash键或字段的序列化器,//可选的设置
        redisTemplate.setHashKeySerializer(keySerializer);

        //使用Jackson2JsonRedisSerializer作为value的序列化器
        //GenericJackson2JsonRedisSerializer来自于spring,不是lettuce.导包别搞错了
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(valueSerializer);
        //可选的设置
        redisTemplate.setHashValueSerializer(valueSerializer);

        return redisTemplate;
    }
}