package zhangjie.api.design.config;

import zhangjie.api.design.annotations.IdentityCardNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @ClassName IdentityCardNumberValidator
 * @Description: 自定义Validator真正进行验证的逻辑代码
 * @author: zhangjie
 * @Date: 2020/10/16 17:30
 **/
public class IdentityCardNumberValidator implements ConstraintValidator<IdentityCardNumber,Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(o)){
            return false;
        }
        String idCard = o.toString();
        return idCard.equals("111") ? true : false;
    }
}
