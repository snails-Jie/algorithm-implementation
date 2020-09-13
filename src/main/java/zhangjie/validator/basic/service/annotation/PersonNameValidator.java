package zhangjie.validator.basic.service.annotation;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @ClassName PersonNameValidator
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/12 22:26
 **/
@Component
public class PersonNameValidator extends ValidatorHandler<String> implements Validator<String> {

    @Override
    public boolean validate(ValidatorContext context, String name) {
        Boolean ignoreName = context.getAttribute("ignoreName",Boolean.class);
        if(Objects.nonNull(ignoreName) && ignoreName){
            return true;
        }
        //模拟服务查询名字
        String niceName = "zhangjie";
        if(niceName.equals(name)){
            context.addError(ValidationError.create("不能取这么好听的名字")
                                .setErrorCode(-1)
                                .setField("name")
                                .setInvalidValue(name)

            );
            return false;
        }
        return true;
    }

}
