package zhangjie.listener.service.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import zhangjie.listener.event.UserRegisterEvent;

/**
 * 1. spring 4.2之后ApplicationEventPublisher自动注入到容器中，不再需要实现Aware接口
 * @ClassName UserServiceImpl
 * @Description: 定义用户注册服务（事件发布者）
 * @author: zhangjie
 * @Date: 2020/9/9 15:28
 **/
@Service
public class UserServiceImpl implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /** 用户注册 */
    public void register(String name){
        System.out.println("用户" + name + "开始注册");
        applicationEventPublisher.publishEvent(new UserRegisterEvent(name));
        System.out.println("用户" + name + "注册完成");
    }

}
