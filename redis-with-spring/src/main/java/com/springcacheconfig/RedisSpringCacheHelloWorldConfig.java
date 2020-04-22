package com.springcacheconfig;

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

import java.time.Duration;

/**
 * 在下面的网址可以针对每一个key设置缓存过期策略.
 * https://segmentfault.com/q/1010000015203664/a-1020000015204174
 *
 *
 * 整个spring容器中只能有一个CachingConfigureSupport的实现类,
 * 所以案例中的3个spring cache相关的配置类,都放在springcacheconfig包下面
 *
 *
 * @author cj
 * @date 2018/11/21
 */
@Configuration
@ComponentScan("com.springcache")
@EnableCaching
public class RedisSpringCacheHelloWorldConfig extends CachingConfigurerSupport {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    //与spring cache整合使用时,不需要再注册RedisTemplate了,本身会自动注册一个.
 /*   @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }*/

  /*  @Override
    public KeyGenerator keyGenerator() {
        KeyGenerator keyGenerator = new SimpleKeyGenerator();

        return keyGenerator;
    }*/

    /*@Override
    public CacheManager cacheManager(){
        //user信息缓存配置
       *//* RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30)).disableCachingNullValues().prefixKeysWith("user");
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("user", userCacheConfiguration);
*//*
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory());

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        //设置默认超过期时间是30秒


        defaultCacheConfig.entryTtl(Duration.ofSeconds(10)).prefixKeysWith("user");

        //初始化RedisCacheManager
        //RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
        return cacheManager;
    }*/

    @Bean  //这个必须加上,否则不会把cacheManager注册上,详见EnableCaching注解的注释
    @Override
    public CacheManager cacheManager( ) {

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(30)) // 设置缓存存活时间
                .disableCachingNullValues(); // 禁止缓存null对象,设置时,需要在cachable注解中配置unless


        return RedisCacheManager
                .builder(redisConnectionFactory())
                .cacheDefaults(cacheConfiguration)
                .build();
    }
}