package zhangjie.aysn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *1. @EnableAsync // 开启 @Async 的支持
 * @Author zhangjie
 * @Date 2020/8/25 21:55
 **/
@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
