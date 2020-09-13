package zhangjie.validator.basic.service.annotation.cascade;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.springframework.context.support.MessageSourceSupport;
import zhangjie.validator.basic.model.annotation.Person;

import java.util.List;

/**
 * @ClassName PersonLimitValidator
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 13:00
 **/
public class PersonLimitValidator extends ValidatorHandler<List<Person>> implements Validator<List<Person>> {
    @Override
    public boolean validate(ValidatorContext context, List<Person> personList) {
        if(personList.size() < 2){
            context.addError(ValidationError.create("人数不足")
                    .setErrorCode(-1)
                    .setField("garage.personList")
                    .setInvalidValue(personList.size())
            );
            return false;
        }

        return true;
    }
}
