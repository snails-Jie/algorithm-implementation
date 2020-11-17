package zhangjie.statemachine.listener;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import zhangjie.statemachine.config.ComplexFormEvents;
import zhangjie.statemachine.config.ComplexFormStates;

/**
 * @ClassName ErrorStateMachineListener
 * @Description: 异常监听器给
 * @author: zhangjie
 * @Date: 2020/11/16 20:54
 **/
public class ErrorStateMachineListener extends StateMachineListenerAdapter<ComplexFormStates, ComplexFormEvents> {
    @Override
    public void stateMachineError(StateMachine<ComplexFormStates, ComplexFormEvents> stateMachine, Exception exception) {
        System.out.println("错误："+exception);
    }
}
