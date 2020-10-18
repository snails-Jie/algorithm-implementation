package zhangjie.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author zhangjie
 * @Date 2020/9/17 21:19
 **/
@SpringBootApplication
@EnableScheduling
@EnableRetry
//@EnableAsync
public class ScheduledApplication{

    public static void main(String[] args) {
        SpringApplication.run(ScheduledApplication.class, args);
        //增加钩子（注释后，DisposableBean依然关闭后执行）
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行 ShutdownHook ....");
            }
        }));
    }
}
