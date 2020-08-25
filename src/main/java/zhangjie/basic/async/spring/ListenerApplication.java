package zhangjie.basic.async.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ListenerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ListenerApplication.class);
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        userService.register("zhangjie");
    }
}
