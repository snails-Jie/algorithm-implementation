package zhangjie.statemachine.config;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import zhangjie.statemachine.config.entity.Form;

/**
 * @ClassName ComplexFormChoiceAction
 * @Description: 分支执行逻辑
 * @author: zhangjie
 * @Date: 2020/11/15 9:00
 **/
public class ComplexFormChoiceAction implements Action<ComplexFormStates,ComplexFormEvents> {
    @Override
    public void execute(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        Form form = context.getMessage().getHeaders().get("form",Form.class);
        System.out.println(form);
        System.out.println(context.getStateMachine().getState());
    }
}
