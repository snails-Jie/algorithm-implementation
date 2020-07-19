package zhangjie.basic.async.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements ApplicationListener<UserRegisterEvent>, Ordered {

    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        System.out.println("用户" +userRegisterEvent.getSource() + "发送Email成功" );
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
