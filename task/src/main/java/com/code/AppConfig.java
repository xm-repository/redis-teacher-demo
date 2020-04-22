package com.code;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cj
 * @date 2018/11/15
 */

@Configuration
@ComponentScan("com.code")
@EnableScheduling
public class AppConfig {
}
