package zhangjie.validator.basic.model.hibernate;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @ClassName Department
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/12 21:49
 **/
public class Department {
    @NotNull
    private Integer id;

    @Length(max = 30)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
