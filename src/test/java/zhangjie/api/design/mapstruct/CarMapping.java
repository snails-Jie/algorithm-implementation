package zhangjie.api.design.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 参考：https://segmentfault.com/a/1190000020663215
 * @ClassName CarMapping
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/15 21:58
 **/
//  CarMapping 注入spring 写法 @Mapper(componentModel = "spring")
@Mapper
public interface CarMapping {
    /**
     * 用来调用实例 实际开发中可使用注入Spring  不写
     */
    CarMapping CAR_MAPPING = Mappers.getMapper(CarMapping.class);

    @Mapping(target = "type", source = "carType.type")
    @Mapping(target = "seatCount", source = "numberOfSeats")
    CarDTO carToCarDTO(Car car);
}
