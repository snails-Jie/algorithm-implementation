package zhangjie.statemachine.web;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.MyStateMachineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhangjie.statemachine.config.ComplexFormEvents;
import zhangjie.statemachine.config.ComplexFormStateMachineBuilder;
import zhangjie.statemachine.config.ComplexFormStates;
import zhangjie.statemachine.config.entity.Form;
import zhangjie.statemachine.listener.ErrorStateMachineListener;

import javax.annotation.Resource;

/**
 * @ClassName StateMachineController
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/11/15 9:17
 **/
@RestController
@RequestMapping("/statemachine")
public class StateMachineController {

    @Resource
    private ComplexFormStateMachineBuilder complexFormStateMachineBuilder;

    @Resource
    private BeanFactory beanFactory;

    @RequestMapping("/testComplexFormState")
    public void testComplexFormState(Integer form) throws Exception {
        StateMachine<ComplexFormStates, ComplexFormEvents> stateMachine = complexFormStateMachineBuilder.build(beanFactory);
        stateMachine.addStateListener(new ErrorStateMachineListener());


        Form form1 = new Form();
        form1.setId(form);
        form1.setFormName(null);

        stateMachine.start();

        Message message = MessageBuilder.withPayload(ComplexFormEvents.WRITE).setHeader("form",form1).build();
        stateMachine.sendEvent(message);
        if(stateMachine.hasStateMachineError()){
            System.out.println("当前状态发生异常");
            MyStateMachineUtils.setCurrentState(stateMachine,ComplexFormStates.BLANK_FORM);
            System.out.println("当前状态：" + stateMachine.getState().getId());
            return;
        }
        message = MessageBuilder.withPayload(ComplexFormEvents.CHECK).setHeader("form", form1).build();
        stateMachine.sendEvent(message);
        System.out.println("当前状态：" + stateMachine.getState().getId());
        message = MessageBuilder.withPayload(ComplexFormEvents.DEAL).setHeader("form", form1).build();
        stateMachine.sendEvent(message);
        System.out.println("当前状态：" + stateMachine.getState().getId());
        message = MessageBuilder.withPayload(ComplexFormEvents.SUBMIT).setHeader("form", form1).build();
        stateMachine.sendEvent(message);
        System.out.println("最终状态：" + stateMachine.getState().getId());

    }
}
