package zhangjie.basic.async.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;
    /**
     * 用户注册后，不再显示调用其他业务的service，而是发布一个用户注册事件
     * @param name
     */
    public void register(String name){
        System.out.println("用户" + name +"开始注册");
        applicationEventPublisher.publishEvent(new UserRegisterEvent(name));
        System.out.println("用户" + name +"注册完成");
    }
}
