package zhangjie.scheduled.task.configtask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 1. 手动设置定时任务的线程池大小
 * 2. 定时任务代码部分还原，不使用@Async注解，新增启动代码配置
 * 3. 对同一个任务不会生效，task1执行的时候需等待上一次执行结束才会触发
 * @Author zhangjie
 * @Date 2020/9/17 21:52
 **/
@Configuration
public class AppConfig implements SchedulingConfigurer {

    @Bean
    public Executor taskExecutor(){
        //指定定时任务线程数量，可根据需求自行调节
        return Executors.newScheduledThreadPool(3);
    }

    /**
     * 指定线程池
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

}
