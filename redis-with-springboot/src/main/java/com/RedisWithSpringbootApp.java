package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cj
 * @date 2020/4/13
 */
@SpringBootApplication
public class RedisWithSpringbootApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RedisWithSpringbootApp.class, args);
    }


    @Autowired
    private  UserServiceImpl userService;
    @Override
    public void run(String... args) throws Exception {
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
