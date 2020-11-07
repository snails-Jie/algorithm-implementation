package zhangjie.spring.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhangjie.spring.aop.aspect.Logging;

/**
 * @ClassName DemoController
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/11/5 22:13
 **/
@RestController
@RequestMapping("/api")
public class DemoController {

    @RequestMapping("/test")
    @Logging
    public String test(String name,String age){
        return "hello,"+name;
    }
}
