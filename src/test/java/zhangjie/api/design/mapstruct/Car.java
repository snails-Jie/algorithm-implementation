package zhangjie.api.design.mapstruct;

import lombok.Data;

/**
 * @ClassName Car
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/15 21:50
 **/
@Data
public class Car {
    private String make;
    private int numberOfSeats;
    private CarType carType;
}
