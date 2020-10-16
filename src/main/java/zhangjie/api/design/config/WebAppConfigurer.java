package zhangjie.api.design.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zhangjie.api.design.interceptor.ResponseResultInterceptor;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @ClassName WebAppConfigurer
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/15 11:48
 **/
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Resource
    private ResponseResultInterceptor responseResultInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseResultInterceptor).addPathPatterns("/**");
    }

    /**
     * JSR和Hibernate validator的校验只能对Object的属性进行校验
     * 不能对单个的参数进行校验
     * spring 在此基础上进行了扩展
     * 添加了MethodValidationPostProcessor拦截器
     * 可以实现对方法参数的校验
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }

    /**
     *  设置快速失败
     * @return
     */
    @Bean
    public static Validator validator(){
        return Validation.byProvider(HibernateValidator.class)
                        .configure()
                        .failFast(true)
                        .buildValidatorFactory()
                        .getValidator();
    }
}
