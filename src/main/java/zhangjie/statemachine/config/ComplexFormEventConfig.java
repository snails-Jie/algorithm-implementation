package zhangjie.statemachine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import zhangjie.statemachine.config.entity.Form;

/**
 * @ClassName ComplexFormEventConfig
 * @Description: 流程事件处理
 * @author: zhangjie
 * @Date: 2020/11/15 9:29
 **/
@WithStateMachine(id="complexFormMachine")
@Slf4j
public class ComplexFormEventConfig {

    @OnTransition(target = "BLANK_FORM")
    public void create(){
        log.info("---空白复杂表单---");
    }

    /**
     * @param message
     * @param stateMachine
     * @return 返回结果无用
     */
    @OnTransition(source = "BLANK_FORM", target = "FULL_FORM")
    public String write(Message<ComplexFormEvents> message, StateMachine<ComplexFormStates,ComplexFormEvents> stateMachine) {
        String result;
        try{
            Form form = (Form) message.getHeaders().get("form");
            System.out.println("传递的参数：" + form);
            int a = 1/form.getId();
            result = "success";
        }catch (Exception e){
            log.error("填写表单异常",e);
            //流程会阻塞
            stateMachine.setStateMachineError(e);
            result = "fail";
        }
        log.info("---填写完复杂表单---");
        return result;
    }

    @OnTransition(source = "FULL_FORM", target = "CHECK_CHOICE")
    public void check(Message<ComplexFormEvents> message) {
        System.out.println("传递的参数：" + message.getHeaders().get("form").toString());
        log.info("---校验复杂表单---");
    }

    //不会执行
    @OnTransition(source = "CHECK_CHOICE", target = "CONFIRM_FORM")
    public void check2confirm(Message<ComplexFormEvents> message) {
        System.out.println("传递的参数：" + message.getHeaders().get("form").toString());
        log.info("---校验表单到待提交表单(choice true)---");
    }

    //不会执行
    @OnTransition(source = "CHECK_CHOICE", target = "DEAL_FORM")
    public void check2deal(Message<ComplexFormEvents> message) {
        System.out.println("传递的参数：" + message.getHeaders().get("form").toString());
        log.info("---校验表单到待提交表单(choice false)---");
    }

    @OnTransition(source = "DEAL_FORM", target = "DEAL_CHOICE")
    public void deal(Message<ComplexFormEvents> message) {
        System.out.println("传递的参数：" + message.getHeaders().get("form").toString());
        log.info("---处理复杂表单---");
    }

    //不会执行
    @OnTransition(source = "DEAL_CHOICE", target = "FAILED_FORM")
    public void deal2fail(Message<ComplexFormEvents> message) {
        System.out.println("传递的参数：" + message.getHeaders().get("form").toString());
        log.info("---处理复杂表单失败(choice false)---");
    }

    //不会执行
    @OnTransition(source = "DEAL_CHOICE", target = "FULL_FORM")
    public void deal2full(Message<ComplexFormEvents> message) {
        System.out.println("传递的参数：" + message.getHeaders().get("form").toString());
        log.info("---处理复杂表单到重新填写表单(choice true)---");
    }



    /**
     * CONFIRM_FORM->SUCCESS_FORM 执行的动作
     */
    @OnTransition(source = "CONFIRM_FORM", target = "SUCCESS_FORM")
    public void submit(Message<ComplexFormEvents> message) {
        System.out.println("传递的参数：" + message.getHeaders().get("form").toString());
        log.info("---表单提交成功---");
    }
}
