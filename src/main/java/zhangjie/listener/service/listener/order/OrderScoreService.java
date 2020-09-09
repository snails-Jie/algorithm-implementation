package zhangjie.listener.service.listener.order;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import zhangjie.listener.event.UserRegisterEvent;

/**
 * @ClassName OrderScoreService
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/9 15:48
 **/
@Component
public class OrderScoreService implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == UserRegisterEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == String.class;
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("用户"+ event.getSource() + "积分初始化成功（有序）");
    }
}
