package zhangjie.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName UserRegisterEvent
 * @Description: 定义用户注册事件
 * @author: zhangjie
 * @Date: 2020/9/9 15:26
 **/
public class UserRegisterEvent extends ApplicationEvent {

    public UserRegisterEvent(Object source) {
        super(source);
    }
}
