package zhangjie.api.design.dto;

import lombok.Data;

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
}
