package zhangjie.validator.basic.model.annotation;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import zhangjie.validator.basic.service.annotation.PersonNameValidator;

/**
 * @ClassName Person
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/12 22:25
 **/
public class Person {

    @FluentValidate({PersonNameValidator.class})
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
