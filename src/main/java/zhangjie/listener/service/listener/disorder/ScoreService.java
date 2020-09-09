package zhangjie.listener.service.listener.disorder;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import zhangjie.listener.event.UserRegisterEvent;

/**
 * @ClassName ScoreService
 * @Description: 积分服务
 * @author: zhangjie
 * @Date: 2020/9/9 15:35
 **/
@Component
public class ScoreService implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println("用户" + event.getSource() + "积分初始化成功");
    }
}
