package zhangjie.scheduled.task.asyn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 1. 由运行日志可见，定时每5s执行一次已生效，且每次任务使用的线程不一样
 * 2. 默认线程池大小为100
 * @Author zhangjie
 * @Date 2020/9/17 21:21
 **/
//@Component
@Slf4j
public class ScheduledTask {

    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("I am asyn task11111111, current thread: {}", Thread.currentThread());
        while (true) {
            //模拟耗时任务，阻塞10s
            Thread.sleep(10000);
            break;
        }
    }

    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void task2() {
        log.info("I am asyn task22222222, current thread: {}", Thread.currentThread());
    }
}
