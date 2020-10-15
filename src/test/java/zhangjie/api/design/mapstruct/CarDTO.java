package zhangjie.api.design.mapstruct;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName CarDTO
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/15 21:56
 **/
@Data
@ToString
public class CarDTO {
    private String make;
    private int seatCount;
    private String type;
}
