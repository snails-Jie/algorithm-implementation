package zhangjie.validator.basic.model.annotation.cascade;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import zhangjie.validator.basic.model.annotation.Person;
import zhangjie.validator.basic.service.annotation.PersonNameValidator;
import zhangjie.validator.basic.service.annotation.cascade.PersonLimitValidator;

import java.util.List;

/**
 * 级联对象图:指一个类嵌套另外一个类的时候做的验证
 * @ClassName Garage
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 12:51
 **/
public class Garage {
    /**
     * @FluentValid注解，表示需要级联到内部Car做onEach验证
     * -----------------------------------
     * 1.调用链会先验证personList上的PersonNameValidator
     * 2.然后再遍历personList，对每个person做内部验证
     */
    @FluentValidate({PersonLimitValidator.class})
    @FluentValid
    private List<Person> personList;

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
