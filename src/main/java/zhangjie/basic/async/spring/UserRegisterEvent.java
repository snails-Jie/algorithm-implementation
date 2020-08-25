package zhangjie.basic.async.spring;

import org.springframework.context.ApplicationEvent;

/**
 * 定义事件发布者
 */
public class UserRegisterEvent extends ApplicationEvent {
    public UserRegisterEvent(Object source) {
        super(source);
    }
}
