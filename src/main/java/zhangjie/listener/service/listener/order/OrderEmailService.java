package zhangjie.listener.service.listener.order;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import zhangjie.listener.event.UserRegisterEvent;

/**
 * @ClassName OrderEmailService
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/9 15:46
 **/
@Component
public class OrderEmailService implements SmartApplicationListener {

    /** 用于指定支持的事件类型 */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == UserRegisterEvent.class;
    }

    /** 支持的目标类型 */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == String.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("用户"+ event.getSource() + "发送Email成功(有序)");
    }

    /** 顺序越小优先级越高 */
    @Override
    public int getOrder() {
        return 0;
    }
}
