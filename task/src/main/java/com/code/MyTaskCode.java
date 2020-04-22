package com.code;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author cj
 * @date 2018/11/15
 */


@Component
public class MyTaskCode {

    /**
     * Scheduled:日程的意思
     * 此注解表明被它修饰的方法是一个定时任务
     * fix:固定的意思
     * rate:频率
     * fixedRate:固定的频率
     * 每隔3秒就运行一次print方法
     *
     * cron是一个专门用来执行调度程序的表达式
     * http://www.bejson.com/othertools/cron/
     * http://www.bejson.com/othertools/cronvalidate/
     *
     * Spring task对cron表达式只支持6位,不支持year(年份)
     * 业界有一个非常强大且出名的调度框架Quartz(石英)
     */
    @Scheduled(fixedRate = 1000)
    //@Scheduled(cron = "1/2 * * * * ?")
    public  void print() throws InterruptedException {
        System.out.println("---print----" + new Date());
        TimeUnit.SECONDS.sleep(4);
    }
}
