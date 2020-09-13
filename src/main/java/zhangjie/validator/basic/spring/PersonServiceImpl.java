package zhangjie.validator.basic.spring;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import org.springframework.stereotype.Service;
import zhangjie.validator.basic.model.annotation.Person;

/**
 * Spring AOP实现业务逻辑和验证逻辑解耦合
 * @ClassName PersonServiceImpl
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 18:23
 **/
@Service
public class PersonServiceImpl implements PersonService {

    /**
     * 1.直接处理真正的核心业务逻辑
     * 2. @FluentValid：Spring利用切面拦截方法，对参数利用FluentValidator做校验
     * @param person
     * @return
     */
    @Override
    public Person addPerson(@FluentValid Person person) {
        System.out.println("Come on! " + person);
        return person;
    }
}
