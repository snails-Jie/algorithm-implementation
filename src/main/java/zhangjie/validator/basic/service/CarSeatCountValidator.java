package zhangjie.validator.basic.service;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 1. 实现Validator接口，泛型T规范这个校验器待验证的对象的类型
 * 2. 继承ValidatorHandler可以避免实现一些默认的方法
 *
 * @Author zhangjie
 * @Date 2020/9/9 21:30
 **/
public class CarSeatCountValidator extends ValidatorHandler<Integer> implements Validator<Integer> {
    /**
     * @param context  校验过程的上下文
     * @param t 第二个参数是待验证对象
     * @return 校验结果
     */
    @Override
    public boolean validate(ValidatorContext context, Integer t) {
        if(t < 2){
            context.addErrorMsg(String.format("Seat count is not valid, invalid value=%s", t));
            return false;
        }
        return true;
    }
}
