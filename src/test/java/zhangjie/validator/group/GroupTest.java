package zhangjie.validator.group;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import zhangjie.validator.basic.model.annotation.group.GroupPerson;
import zhangjie.validator.basic.service.annotation.group.Add;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

/**
 * @ClassName GroupTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 12:06
 **/
public class GroupTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator(){
        //设置语言
        Locale.setDefault(Locale.ENGLISH);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    /**
     * 1. 场景：某些时候例如添加操作，我们会验证A/B/C三个属性
     *         而修改操作，我们需要验证B/C/D/E 4个属性
     * 2.验证的时候，只需要在checkAll()方法中传入想要验证的group，就只会做选择性的分组验证
     */
    @Test
    public void test(){
        GroupPerson person = new GroupPerson();
        person.setName("ssss");
        person.setAlias("zhangjie");

        Result ret = FluentValidator.checkAll(new Class<?>[] {Add.class})
                .on(person)
                .doValidate()
                .result(toSimple());
        System.out.println(ret);
    }

    /**
     * 不指定分组，则全部校验
     */
    @Test
    public void NoGroupTest(){
        GroupPerson person = new GroupPerson();
        person.setName("ssss");
        person.setAlias("zhangjie");

        Result ret = FluentValidator.checkAll()
                .on(person)
                .doValidate()
                .result(toSimple());
        System.out.println(ret);
    }

    /**
     * groups的方法同样适用于hibernate validator
     */
    @Test
    public void hibernateTest(){
        GroupPerson person = new GroupPerson();

        Result ret = FluentValidator.checkAll(new Class<?>[] {Add.class})
                .on(person, new HibernateSupportedValidator<GroupPerson>().setHiberanteValidator(validator))
                .on(person)
                .doValidate()
                .result(toSimple());
        System.out.println(ret);
    }
}
