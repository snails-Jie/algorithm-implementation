package zhangjie.statemachine.config;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import zhangjie.statemachine.config.entity.Form;

/**
 * @ClassName ComplexFormCheckChoiceGuard
 * @Description: 校验表单 ： 1. 待提交表单
 *                          2. 待处理表单
 * @author: zhangjie
 * @Date: 2020/11/15 8:54
 **/
public class ComplexFormCheckChoiceGuard implements Guard<ComplexFormStates,ComplexFormEvents> {
    @Override
    public boolean evaluate(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        boolean returnValue = false;
        Form form = context.getMessage().getHeaders().get("form",Form.class);
        if(form.getFormName() == null){
            returnValue = false;
        }else{
            returnValue = true;
        }
        return returnValue;
    }
}
