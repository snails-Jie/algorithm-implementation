package zhangjie.validator.basic.model.hibernate;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Company
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/12 21:40
 **/
public class Company {
    @NotEmpty
    @Pattern(regexp = "[0-9a-zA-Z\4e00-\u9fa5]+")
    private String name;

    @NotNull(message = "The establishTime must not be null")
    private Date establishTime;

    @NotNull
    @Size(min = 0, max = 10)
    @Valid
    private List<Department> departmentList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }
}
