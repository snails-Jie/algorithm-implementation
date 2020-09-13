package zhangjie.validator.annotation;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.registry.impl.SimpleRegistry;
import org.junit.Test;
import zhangjie.validator.basic.model.annotation.Person;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

/**
 * 注解验证
 * 1. @FluentValidate可以装饰在属性上，内部接收一个Class[]数组参数
 *   1.1 某个属性上依次用这些Validator做验证
 * @ClassName AnnotationTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/12 22:20
 **/
public class AnnotationTest {

    /**
     *1. 默认的，FluentValidator使用SimpleRegistry
     *  1.1 会尝试从当前的class loader中调用Class.newInstance()方法来新建一个Validator
     *2. 如果你的验证器需要Spring IoC容器管理的bean注入
     *   2.1 使用SpringApplicationContextRegistry：去Spring的容器中寻找Validator
     *   2.2 使用Spring增强功能：引入fluent-validator-spring
     */
    @Test
    public void test(){
        Person person = new Person();
        person.setName("zhangjie");

        Result ret = FluentValidator.checkAll().configure(new SimpleRegistry())
                .on(person)
                .doValidate()
                .result(toSimple());
        System.out.println(ret);
    }
}
