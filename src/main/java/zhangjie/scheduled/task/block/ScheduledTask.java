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
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("I am task11111111, current thread: {}", Thread.currentThread());
        while (true) {
            //模拟耗时任务，阻塞10s
            Thread.sleep(10000);
            break;
        }
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void task2() {
        log.info("I am task22222222, current thread: {}", Thread.currentThread());
    }
}
