package com.config;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;


/**
 * 可选的可以实现一些Customizer接口对自动配置的类型进行调整
 * 比如CacheManagerCustomizer,RedisCacheManagerBuilderCustomizer接口等
 *
 * @author cj
 * @date 2018/11/21
 * <p>
 * 装配RedisCacheManager，这里初始化了cache1和cache2两个缓存，并存入Map中,
 * 后续在使用时可以指定操作哪一个缓存。见@Cacheable注解的value的值,
 * 没有指定用哪一个缓存配置就使用默认配置,比如缓存永不过期
 * @return
 */
/*
@Configuration
@EnableCaching
public class RedisSpringbootConfig {


    private RedisCacheConfiguration initRedisCacheConfiguration(Long ttl) {

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        //SerializationPair 序列化器对指的是序列化(写)与反序列化(读)这一对操作,一般用同一个序列化方式
        return cacheConfiguration
                //设置key的序列化器
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new 	StringRedisSerializer()))
                //设置value的序列化器
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new 	GenericJackson2JsonRedisSerializer()))
                //设置缓存过期时间
                .entryTtl(Duration.ofSeconds(ttl))
                .disableCachingNullValues()
                .computePrefixWith(cacheName -> "projectname".concat(":").concat(cacheName).concat(":"));

    }

    */
/**
 * 装配RedisCacheManager，这里初始化了cache1和cache2两个缓存，并存入Map中,
 * 后续在使用时可以指定操作哪一个缓存。见@Cacheable注解的value的值,
 * 没有指定用哪一个缓存配置就使用默认配置,比如缓存永不过期
 * @return
 */
/*

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        Map<String, RedisCacheConfiguration> map = new HashMap<>(2);
        //cache1称之为命名空间名,可以理解为一个缓存的配置名
        map.put("cache1", initRedisCacheConfiguration(1800L));
        map.put("cache2", initRedisCacheConfiguration(3600L));

        RedisCacheManager cacheManager = RedisCacheManager
                .builder(redisConnectionFactory)
                .withInitialCacheConfigurations(map)
                .build();

        return cacheManager;
    }

}*/

/**
 * 下面这个类演示Customizer接口的使用
 */
@Configuration
@EnableCaching
public class RedisSpringbootConfig implements RedisCacheManagerBuilderCustomizer {


    private RedisCacheConfiguration initRedisCacheConfiguration(Long ttl) {

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        //SerializationPair 序列化器对指的是序列化(写)与反序列化(读)这一对操作,一般用同一个序列化方式
        return cacheConfiguration
                //设置key的序列化器,fromSerializer是个方法的静态导入形式
                .serializeKeysWith(
                        fromSerializer(RedisSerializer.string()))
                //设置value的序列化器
                .serializeValuesWith(
                        fromSerializer(RedisSerializer.json()))
                //设置缓存过期时间
                .entryTtl(Duration.ofSeconds(ttl))
                .disableCachingNullValues()
                .computePrefixWith(cacheName -> "projectname".concat(":").concat(cacheName).concat(":"));

    }


    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>(2);
        //cache1称之为命名空间名,可以理解为一个缓存的配置名
        configurationMap.put("cache1", initRedisCacheConfiguration(1800L));
        configurationMap.put("cache2", initRedisCacheConfiguration(3600L));

        //见源码中的RedisCacheConfiguration类的cacheManager方法,这里不需要调用builder,cacheDefault,build方法.
        builder.withInitialCacheConfigurations(configurationMap);
    }
}