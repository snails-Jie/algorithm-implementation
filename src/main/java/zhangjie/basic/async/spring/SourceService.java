package zhangjie.basic.async.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SourceService  {

   @EventListener({UserRegisterEvent.class,UserRegisterCompleteEvent.class})
   @Order(2)
   @Async
    public void sourceEventListener(ApplicationEvent event) {
       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       System.out.println("用户" +event.getSource() + "初始化成功" );
    }
}
