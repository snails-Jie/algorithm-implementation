package zhangjie.basic.async.spring;

import org.springframework.context.ApplicationEvent;

public class UserRegisterCompleteEvent extends ApplicationEvent {
    public UserRegisterCompleteEvent(Object source) {
        super(source);
    }
}
