package com.springcache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

/**
 * 在下面的网址可以针对每一个key设置缓存过期策略.
 * https://segmentfault.com/q/1010000015203664/a-1020000015204174
 *
 * @author cj
 * @date 2018/11/21
 */
@Configuration
@EnableCaching
@ComponentScan("com.springcache")
public class RedisMultiSpringCacheConfig extends CachingConfigurerSupport {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }



    /**
     * 装配RedisCacheManager，这里初始化了cache1和cache2两个缓存，并存入Map中,
     * 后续在使用时可以指定操作哪一个缓存。见@Cacheable注解的value的值,
     * 没有指定用哪一个缓存配置就使用默认配置,比如缓存永不过期
     * @return
     */

    @Override
    @Bean  //一定要加
    public CacheManager cacheManager() {
        Map<String, RedisCacheConfiguration> map = new HashMap<>(2);

        //cache1称之为命名空间名,可以理解为一个缓存的配置名
        map.put("cache1", initRedisCacheConfiguration(1800L));
        map.put("cache2", initRedisCacheConfiguration(3600L));

        RedisCacheManager cacheManager = RedisCacheManager
                .builder(redisConnectionFactory())
                //.cacheDefaults() //即使不设置默认的缓存配置,也会有一个默认的设置,见RedisCacheManager源码,字段初始赋值了
                .withInitialCacheConfigurations(map)
                .build();

        return cacheManager;
    }


    private RedisCacheConfiguration initRedisCacheConfiguration(Long ttl) {

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig();

        //SerializationPair 序列化器对指的是序列化(写)与反序列化(读)这一对操作,一般用同一个序列化方式
        return cacheConfiguration
                //设置key的序列化器
                .serializeKeysWith(fromSerializer(RedisSerializer.string()))
                //设置value的序列化器
                .serializeValuesWith(fromSerializer(RedisSerializer.json()))
                //设置缓存过期时间
                .entryTtl(Duration.ofSeconds(ttl))
                .computePrefixWith(cacheName -> "projectname".concat(":").concat(cacheName).concat(":"));
    }
}