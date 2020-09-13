package zhangjie.validator.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import zhangjie.validator.basic.model.annotation.Person;
import zhangjie.validator.basic.spring.PersonException;
import zhangjie.validator.basic.spring.PersonService;

/**
 * @ClassName SpringAopTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 18:41
 **/
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class SpringAopTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private PersonService personService;

    @Test
    public void test(){
        try{
            Person person = new Person();
            person.setName("zhangjie");

            personService.addPerson(person);
        }catch (PersonException e){
            System.out.println("发生异常："+e);
        }

    }
}
