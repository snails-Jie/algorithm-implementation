package zhangjie.listener.service.listener.disorder;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import zhangjie.listener.event.UserRegisterEvent;

/**
 * 1. 注解式的事件订阅者：@EventListener注解完成了ApplicationListener<E extends ApplicationEvent>接口的使命
 * 2. 对异步事件机制的支持：
 *   2.1 通过@EnableAsync模块注解开启异步支持
 *   2.2 使用@Async注解对需要异步的监听器进行标注
 * @ClassName EmailService
 * @Description: 邮件服务
 * @author: zhangjie
 * @Date: 2020/9/9 15:33
 **/
@Component
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println("用户" + event.getSource() + "发送Email成功");
    }
}
