package zhangjie.api.design.dto;

import lombok.Data;
import zhangjie.api.design.annotations.Create;
import zhangjie.api.design.annotations.IdentityCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName User
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/15 20:43
 **/
@Data
public class User {
    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "age不能为空")
    @Max(value = 12, message = "最大为12")
    private Integer age;

    @NotNull(message = "idCard不能为空",groups = Create.class)
    @IdentityCardNumber(message = "idCard错误",groups = Create.class)
    private String idCard;
}
