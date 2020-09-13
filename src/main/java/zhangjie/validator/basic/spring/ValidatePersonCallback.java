package zhangjie.validator.basic.spring;

import com.baidu.unbiz.fluentvalidator.DefaultValidateCallback;
import com.baidu.unbiz.fluentvalidator.ValidateCallback;
import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.validator.element.ValidatorElementList;

import java.util.List;

/**
 * 1. ValidateCallback表示该如何处理成功，失败，抛出不可控异常三种情况
 *      1.1 即使你拿不到Result结果，也可以做自己的操作
 * @ClassName ValidateCallback
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 18:25
 **/
public class ValidatePersonCallback extends DefaultValidateCallback implements ValidateCallback {

    @Override
    public void onFail(ValidatorElementList validatorElementList, List<ValidationError> errors) {
        throw new PersonException(errors.get(0).getErrorMsg());
    }

    @Override
    public void onSuccess(ValidatorElementList validatorElementList) {
        System.out.println("Everything works fine!");
    }

    @Override
    public void onUncaughtException(Validator validator, Exception e, Object target) throws Exception {
        throw new PersonException(e);
    }
}
