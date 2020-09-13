package zhangjie.validator.jsr;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import zhangjie.validator.basic.model.hibernate.Company;
import zhangjie.validator.basic.model.hibernate.Department;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

/**
 * @ClassName JsrTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/12 21:55
 **/
public class JsrTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator(){
        //设置语言
        Locale.setDefault(Locale.ENGLISH);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * HibernateSupportedValidator依赖于javax.validation.Validator的实现，也就是Hibernate Validator
     */
    @Test
    public void test(){
        List<Department> departmentList = new ArrayList<>();

        Company company = new Company();
        company.setDepartmentList(departmentList);
        company.setEstablishTime(new Date());


        Result ret = FluentValidator.checkAll()
                .on(company, new HibernateSupportedValidator<Company>().setHiberanteValidator(validator))
                .on(company, new ValidatorHandler<Company>(){
                    @Override
                    public boolean validate(ValidatorContext context, Company company) {
                        return true;
                    }
                })
                .doValidate().result(toSimple());
        System.out.println(ret);
    }
}
