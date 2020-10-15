package zhangjie.api.design.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zhangjie.api.design.interceptor.ResponseResultInterceptor;

import javax.annotation.Resource;

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
}
