package zhangjie.scheduled.task.block;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务默认使用单线程:scheduling-1
 * @Author zhangjie
 * @Date 2020/9/17 21:21
 **/
@Component
@Slf4j
public class ScheduledTask {
    @Scheduled(cron = "0/20 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("I am task11111111, current thread: {}", Thread.currentThread());
        for(int i=0;i<10;i++){
            Thread.sleep(1000);
            System.out.println("循环.....");
        }
        System.out.println("循环完毕");
    }

//    @Scheduled(cron = "0/5 * * * * ?")
////    public void task2() {
////        log.info("I am task22222222, current thread: {}", Thread.currentThread());
////    }
}
