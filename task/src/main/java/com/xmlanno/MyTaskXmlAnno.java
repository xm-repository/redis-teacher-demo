package com.xmlanno;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author cj
 * @date 2018/11/15
 */
@Component
public class MyTaskXmlAnno {
    @Scheduled(fixedRate = 1000*2)
    public  void print(){
        System.out.println("---print----");
    }
}
