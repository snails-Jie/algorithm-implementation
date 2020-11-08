package zhangjie.state.cases;

import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import zhangjie.state.enums.Events;
import zhangjie.state.enums.States;

import javax.annotation.Resource;

/**
 * @ClassName StateMachineStart
 * @Description: 模拟客户端触发事件，发送事件到状态机
 * @author: zhangjie
 * @Date: 2020/9/20 10:05
 **/
@Component
public class StateMachineStart implements CommandLineRunner {

    @Resource
    StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(Events.PUBLISH);
        stateMachine.sendEvent(Events.ONLINE);
        stateMachine.sendEvent(Events.ROLLBACK);
    }
}
