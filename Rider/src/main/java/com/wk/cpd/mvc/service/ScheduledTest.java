package com.wk.cpd.mvc.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wk.cpd.mvc.utils.DataUtils;

/**
 * @description: 
 */
@Component
public class ScheduledTest {

    @Async
    @Scheduled(cron="*/10 * * * * ?")
    public void doSomething5() throws Exception {
        TimeUnit.SECONDS.sleep(20);
        System.out.println("cron============================:" + new Date());
    }
    
    @Async
    @Scheduled(fixedRate=5000)
    public void doSomething2() {
        System.out.println("fixedRate+++++++++++++++++++++++++:" + new Date());
    }
    
//    @Scheduled(fixedDelay=16000)
//    public void doSomething7() {
//        System.out.println("fixedRate---------------------------:" + new Date());
//    }
}
