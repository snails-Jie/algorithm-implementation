package zhangjie.validator.basic.model.annotation.group;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import zhangjie.validator.basic.service.annotation.PersonNameValidator;
import zhangjie.validator.basic.service.annotation.group.Add;
import zhangjie.validator.basic.service.annotation.group.Insert;

import javax.validation.constraints.NotNull;

/**
 * @ClassName GroupPerson
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/13 12:07
 **/
public class GroupPerson {
    @FluentValidate(value = {PersonNameValidator.class},groups = {Add.class})
    private String name;

    @FluentValidate(value = {PersonNameValidator.class})
    private String alias;

    @NotNull(groups = {Insert.class})
    private String sex;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
