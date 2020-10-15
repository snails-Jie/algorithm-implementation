package zhangjie.api.design;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Test;
import zhangjie.api.design.entity.Dest;
import zhangjie.api.design.entity.Original;
import zhangjie.api.design.mapstruct.Car;
import zhangjie.api.design.mapstruct.CarDTO;
import zhangjie.api.design.mapstruct.CarMapping;
import zhangjie.api.design.mapstruct.CarType;

/**
 * @ClassName ConverTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/15 21:21
 **/
public class Convert {
    private Original o = new Original();

    private Car car = new Car();

    @Before
    public void init(){
        o.setId(7);
        o.setName("七夕快乐");
        o.setDesc("老婆给我最好的七夕礼物是给我独处的一天");


        CarType carType = new CarType();
        carType.setType("type");

        car.setMake("make");
        car.setNumberOfSeats(2);
        car.setCarType(carType);
    }

    @Test
    public void propertyUtilTest() throws Exception {
        Dest d = new Dest();
        PropertyUtils.copyProperties(d,o);
        System.out.println(d);
    }

    /**
     * 利用反射机制对JavaBean的属性进行处理(性能差)
     * @throws Exception
     */
    @Test
    public void beanUtilsTest() throws Exception {
        Dest d = new Dest();
        BeanUtils.copyProperties(d,o);
        System.out.println(d);
    }

    /**
     * 编译器完成转换
     * 步骤：
     *  1. 引包
     *  2. 安装 MapStruct Plugin 插件,当然不安装也是可以的
     *
     */
    @Test
    public void mapStructTest(){
        CarDTO carDTO = CarMapping.CAR_MAPPING.carToCarDTO(car);
        System.out.println(carDTO);
    }

}
