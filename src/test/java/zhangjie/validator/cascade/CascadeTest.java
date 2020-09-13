package zhangjie.validator.cascade;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import org.junit.Test;
import zhangjie.validator.basic.model.annotation.Person;
import zhangjie.validator.basic.model.annotation.cascade.Garage;
import zhangjie.validator.basic.service.annotation.cascade.PersonLimitValidator;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

/**
 * @ClassName CascadeTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 12:53
 **/
public class CascadeTest {


    @Test
    public void test(){
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setName("zhangjie");
        list.add(person);
        Garage garage = new Garage();
        garage.setPersonList(list);

        Result ret = FluentValidator.checkAll()
                .on(garage.getPersonList(),new PersonLimitValidator())
                .on(garage)
                .doValidate()
                .result(toSimple());
        System.out.println(ret);
    }
}
