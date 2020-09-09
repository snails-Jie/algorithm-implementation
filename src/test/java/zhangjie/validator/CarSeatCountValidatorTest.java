package zhangjie.validator;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import org.junit.Test;
import zhangjie.validator.basic.model.Car;
import zhangjie.validator.basic.service.CarSeatCountValidator;

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
}
