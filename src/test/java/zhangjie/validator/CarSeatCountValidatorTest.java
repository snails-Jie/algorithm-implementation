package zhangjie.validator;

import com.baidu.unbiz.fluentvalidator.*;
import com.baidu.unbiz.fluentvalidator.exception.RuntimeValidateException;
import com.baidu.unbiz.fluentvalidator.validator.element.ValidatorElementList;
import org.junit.Test;
import zhangjie.validator.basic.model.Car;
import zhangjie.validator.basic.service.CarSeatCountValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

/**
 * @Author zhangjie
 * @Date 2020/9/9 21:34
 **/
public class CarSeatCountValidatorTest {

    @Test
    public void test(){
        Car car = new Car();
        car.setSeatCount(1);

        Result ret = FluentValidator.checkAll()
                .on(car.getSeatCount(), new CarSeatCountValidator())
                .doValidate()
                .result(toSimple());

        System.out.println(ret);
    }

    @Test
    public void testError(){
        Car car = new Car();
        car.setSeatCount(1);

        Result ret = FluentValidator.checkAll()
                .on(car.getSeatCount(), new CarSeatCountValidator())
                .doValidate(new DefaultValidateCallback(){
                    @Override
                    public void onFail(ValidatorElementList validatorElementList, List<ValidationError> errors) {
                        System.out.println("验证异常");
                    }
                })
                .result(toSimple());
        System.out.println(ret);
    }

    @Test
    public void testRuntimeValidateException(){
        Car car = new Car();
        car.setSeatCount(1);
        try{
            Result ret = FluentValidator.checkAll()
                    .on(car.getSeatCount(), new ValidatorHandler<Integer>(){
                        @Override
                        public boolean validate(ValidatorContext context, Integer integer) {
                            int i = 1/0;
                            return true;
                        }
                    })
                    .doValidate()
                    .result(toSimple());
            System.out.println(ret);
        }catch (RuntimeValidateException validateException){
            System.out.println("发生异常:"+validateException);
        }


    }

    /**
     * context上下文共享
     */
    @Test
    public void testContext(){
        Car car = new Car();
        car.setSeatCount(1);

        Result ret = FluentValidator.checkAll()
                .putAttribute2Context("ignoreAttr",true)
                .on(car.getSeatCount(),new ValidatorHandler<Integer>(){
                    @Override
                    public boolean validate(ValidatorContext context, Integer seatCount) {
                        Boolean ignore = context.getAttribute("ignoreAttr",Boolean.class);
                        if(Objects.nonNull(ignore) && ignore){
                            System.out.println("有ignore属性，无需校验");
                            return true;
                        }
                        if(seatCount < 2){
                            return false;
                        }else{
                            return true;
                        }
                    }
                })
                .doValidate()
                .result(toSimple());
        System.out.println(ret);
    }

    /**
     * 1. 作用:在Validator内部可以调用，并且缓存结果到Closure中
     *  1.1 caller在上层可以获取这个结果
     * 2. 应用场景:当需要频繁调用一个RPC的时候，往往该执行线程内部一次调用就够了
     *    2.1 缓存住这个结果，在所有Validator间和caller中共享
     */
    @Test
    public void testClosure(){
        Car car = new Car();
        car.setSeatCount(1);
        car.setManufacturer("test");

        Result ret = FluentValidator.checkAll()
                .putClosure2Context("closure", new ClosureHandler<List<String>>() {
                    private List<String> allManufacturers;

                    @Override
                    public List<String> getResult() {
                        return allManufacturers;
                    }


                    @Override
                    public void doExecute(Object... input) {
                        //模拟调用远程服务获取
                        allManufacturers = new ArrayList<String>();
                        allManufacturers.add("test");
                        allManufacturers.add("zj");
                    }


                })
                .on(car, new ValidatorHandler<Car>(){
                    @Override
                    public boolean validate(ValidatorContext context, Car car) {
                        Closure<List<String>> closure = context.getClosure("closure");
                        List<String> allManufacturers = closure.executeAndGetResult();
                        if(!allManufacturers.contains(car.getManufacturer())){
                            return false;
                        }
                        return true;
                    }
                })
                .doValidate()
                .result(toSimple());

        System.out.println(ret);
    }



}
