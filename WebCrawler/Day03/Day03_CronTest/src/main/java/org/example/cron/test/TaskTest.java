package org.example.cron.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author HackerStar
 * @create 2020-05-26 10:21
 */
//@Component
public class TaskTest {
    @Scheduled(cron = "0/5 * * * * *")
    public void test () {
        System.out.println(LocalDateTime.now()+"任务执行了");
    }
}
