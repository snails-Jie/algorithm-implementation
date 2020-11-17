package zhangjie.statemachine.config;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import zhangjie.statemachine.config.entity.Form;

/**
 * @ClassName ComplexFormDealChoiceGuard
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/11/15 9:10
 **/
public class ComplexFormDealChoiceGuard implements Guard<ComplexFormStates,ComplexFormEvents> {
    @Override
    public boolean evaluate(StateContext<ComplexFormStates, ComplexFormEvents> stateContext) {
        boolean returnValue = false;
        Form form = stateContext.getMessage().getHeaders().get("form",Form.class);
        if(form.getFormName() == null || form.getFormName().indexOf("坏") > -1 ){
            returnValue = false;
        }else {
            returnValue = true;
        }
        return returnValue;
    }
}
