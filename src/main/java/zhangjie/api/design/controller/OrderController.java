package zhangjie.api.design.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhangjie.api.design.annotations.Create;
import zhangjie.api.design.annotations.IdentityCardNumber;
import zhangjie.api.design.annotations.ResponseResult;
import zhangjie.api.design.annotations.Update;
import zhangjie.api.design.dto.Order;
import zhangjie.api.design.dto.User;
import zhangjie.api.design.enums.ResultCode;
import zhangjie.api.design.response.Result;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @ClassName OrderController
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/15 10:40
 **/
@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    /**
     * 问题：
     * 1. 每个方法的返回都是Result封装对象，没有业务含义
     * 2. 在业务代码中，成功的时候我们调用Result.success，异常错误调用Result.failure。是不是很多余
     * 3. 上面的代码，判断id是否为null，其实我们可以使用hibernate validate做校验，没有必要在方法体中做判断
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result getOrder(@PathVariable("id") Integer id){
        if(id == null){
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        Order order = new Order(id);
        return Result.success(order);
    }

    /**
     * 最好的方式直接返回真实业务对象，最好不要改变之前的业务方式
     * 1. 实现思路
     *  1.1 定义一个注解@ResponseResult，表示这个接口返回的值需要包装一下
     *  1.2 拦截请求，判断此请求是否需要被@ResponseResult注解
     *  1.3 核心步骤就是实现接口ResponseBodyAdvice和@ControllerAdvice，判断是否需要包装返回值，如果需要，就把Controller接口的返回值进行重写
     * @param id
     * @return
     */
    @GetMapping("/opt/{id}")
    @ResponseResult
    public Order getOptOrder(@PathVariable("id") Integer id){
        if(id == 1){
            int b = 1/0;
        }
        Order order = new Order(id);
        return order;
    }

    @GetMapping("/validator")
    public Result validator(@NotNull(message = "姓名不能为空") String name){
        if("zj".equals(name)){
            int a = 1/0;
        }
        return Result.success("你好，" + name);
    }

    @GetMapping("/validator/user")
    public Result validatorUser(@Valid User user){
        return Result.success("你好，" + user.getName());
    }

    /** 校验分组 */
    @GetMapping("/validator/user/group/update")
    public Result validatorGroupUpdateUser(@Validated(Update.class) User user){
        return Result.success("你好，" + user.getName());
    }

    @GetMapping("/validator/user/group/create")
    public Result validatorGroupCreateUser(@Validated(Create.class) User user){
        return Result.success("你好，" + user.getName());
    }

    /** 自定义校验注解 */
    @GetMapping("/validator/customer")
    public Result customerValidator(@IdentityCardNumber(message = "身份证号码不是111") String carNo){
        return Result.success("身份证号码，" + carNo);
    }

}
