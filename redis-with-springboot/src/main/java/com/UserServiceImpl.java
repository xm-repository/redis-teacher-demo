package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cj
 * @date 2018/11/21
 */
@Service
//进行类级别的缓存设置,常见的设置是设置缓存配置的名字,方法上的设置会覆盖掉此设置
@CacheConfig(cacheNames = {"cache1"})
public class UserServiceImpl {
    //在配置类中不需要配置RedisTemplate,会有一个默认的,
    // 但此默认的RedisTemplate没有用到你配置的CacheManager中设置的设置,比如序列化等
    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    private static Map<Integer, UserInfo> userInfoMap;

    static {
        userInfoMap = new HashMap<>();

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setName("aa");
        userInfoMap.put(1, userInfo);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setId(2);
        userInfo2.setName("bb");
        userInfoMap.put(2, userInfo2);
    }


    /**
     * 这里指定的缓存名为cache1,与配置中的名字一样,缓存的过期时间为1800
     * 如果指定的不是配置中的名字,就会使用默认的配置,比如过期时间是-1(永不过期)
     * <p>
     * unless的配置是spring的表达式方式,#result代表方法的返回值
     * #id就是方法的参数,比如#id.length()<=4 表示方法的参数的长度<=4
     *
     * @param id
     * @return
     */
    @Cacheable(value = "cache1", key = "#root.targetClass.name + ':'+ #root.methodName + ':' + #id")
    public UserInfo getById(Integer id) {

        System.out.println("-----create a new userinfo---");
        UserInfo userInfo = userInfoMap.get(id);
        return userInfo;
    }

    @CacheEvict(value = "cache1", key = "#id")
    public void deleteById(Integer id) {
        System.out.println("---deleting---");
        userInfoMap.remove(id);
    }
}
