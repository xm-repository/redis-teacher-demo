package com.springcache;

import com.springcacheconfig.RedisSpringCacheHelloWorldConfig;
import com.springcacheconfig.RedisSpringCacheWithJacksonSerializerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cj
 * @date 2018/11/21
 */
public class Main {
    public static void main(String[] args) {
        //CachingConfigurer的实现类只能有一个,所以所有的配置类都放在了springcacheconfig包下
        // 把此包中的类一个个的拖过来演示即可

        //springCacheHelloWorld();
        //springCacheWithJacksonSerializer();
        springCacheWithMultiCacheConfig();

    }

    private static void springCacheHelloWorld() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisSpringCacheHelloWorldConfig.class);
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        UserInfo userInfo = userService.getById(1);
        System.out.println("main111--: " + userInfo);

        UserInfo userInfo2 = userService.getById(1);
        System.out.println("main 222--: " + userInfo2);

        UserInfo userInfo3 = userService.getById(2);
        System.out.println("main 333--: " + userInfo3);

        System.out.println("--prepare delete--");
        userService.deleteById(1);
        System.out.println("-- delete completed--");

        UserInfo userInfo4 = userService.getById(1);
        System.out.println("main 444--: " + userInfo4);

        UserInfo userInfo5 = userService.getById(2);
        System.out.println("main 555--: " + userInfo5);
    }


    //TODO:
    private static void springCacheWithJacksonSerializer() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisSpringCacheWithJacksonSerializerConfig.class);
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        UserInfo userInfo = userService.getById(1);
        System.out.println("main111--: " + userInfo);

        UserInfo userInfo2 = userService.getById(1);
        System.out.println("main 222--: " + userInfo2);

        UserInfo userInfo3 = userService.getById(2);
        System.out.println("main 333--: " + userInfo3);

        System.out.println("--prepare delete--");
        // userService.deleteById(1);
        System.out.println("-- delete completed--");

        UserInfo userInfo4 = userService.getById(1);
        System.out.println("main 444--: " + userInfo4);

        UserInfo userInfo5 = userService.getById(2);
        System.out.println("main 555--: " + userInfo5);
    }


    private static void springCacheWithMultiCacheConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisMultiSpringCacheConfig.class);
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        UserInfo userInfo = userService.getById(1);
        System.out.println("main111--: " + userInfo);

        UserInfo userInfo2 = userService.getById(1);
        System.out.println("main 222--: " + userInfo2);

        UserInfo userInfo3 = userService.getById(2);
        System.out.println("main 333--: " + userInfo3);

        System.out.println("--prepare delete--");
        // userService.deleteById(1);
        System.out.println("-- delete completed--");

        UserInfo userInfo4 = userService.getById(1);
        System.out.println("main 444--: " + userInfo4);

        UserInfo userInfo5 = userService.getById(2);
        System.out.println("main 555--: " + userInfo5);
    }
}
