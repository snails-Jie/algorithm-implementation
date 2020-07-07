package zhangjie.spring.beanlife;

/**
 * @Author zhangjie
 * @Date 2020/7/7 17:31
 **/
public class Student {
    private String name;
    private Integer age;

    public Student() {
        System.out.println("初始化Student");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【Student】调用setName");
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("【Student】调用setAge");
        this.age = age;
    }

    public void myInit(){
        System.out.println("【Student】调用myInit");
    }

    public void myDestory(){
        System.out.println("【Student】调用myDestory");
    }
}
